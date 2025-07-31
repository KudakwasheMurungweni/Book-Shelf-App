package zw.co.BookShelf.BookApp.Service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import zw.co.BookShelf.BookApp.dto.GoogleBookPreviewDto;
import zw.co.BookShelf.BookApp.dto.GoogleBookSearchResponseDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleBookService {

    private final RestTemplate restTemplate;

    @Value("${google.books.api.url:https://www.googleapis.com/books/v1/volumes}")
    private String googleBooksApiUrl;

    public GoogleBookService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GoogleBookSearchResponseDto searchBooks(String query, int startIndex, int maxResults) {
        String url = String.format("%s?q=%s&startIndex=%d&maxResults=%d", googleBooksApiUrl, query, startIndex, maxResults);
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
        JsonNode root = response.getBody();

        List<GoogleBookPreviewDto> books = new ArrayList<>();
        if (root != null && root.has("items")) {
            for (JsonNode item : root.get("items")) {
                JsonNode info = item.get("volumeInfo");
                GoogleBookPreviewDto dto = new GoogleBookPreviewDto();
                dto.setGoogleBookId(item.get("id").asText());
                dto.setTitle(info.path("title").asText());
                dto.setAuthors(extractList(info.path("authors")));
                dto.setPublishedDate(info.path("publishedDate").asText());
                dto.setDescription(info.path("description").asText(""));
                dto.setPageCount(info.path("pageCount").asInt(0));
                dto.setCategories(extractList(info.path("categories")));
                dto.setThumbnailUrl(info.path("imageLinks").path("thumbnail").asText(""));
                books.add(dto);
            }
        }

        GoogleBookSearchResponseDto result = new GoogleBookSearchResponseDto();
        result.setBooks(books);
        result.setTotalItems(root != null ? root.path("totalItems").asInt() : 0);
        return result;
    }

    // Example: Search by title only (returns first match or null)
    public GoogleBookPreviewDto searchByTitle(String title) {
        String url = googleBooksApiUrl + "?q=intitle:" + title;
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
        JsonNode root = response.getBody();

        if (response.getStatusCode().is2xxSuccessful() && root != null && root.has("items")) {
            JsonNode item = root.get("items").get(0);
            JsonNode info = item.get("volumeInfo");
            GoogleBookPreviewDto dto = new GoogleBookPreviewDto();
            dto.setGoogleBookId(item.get("id").asText());
            dto.setTitle(info.path("title").asText());
            dto.setAuthors(extractList(info.path("authors")));
            dto.setPublishedDate(info.path("publishedDate").asText());
            dto.setDescription(info.path("description").asText(""));
            dto.setPageCount(info.path("pageCount").asInt(0));
            dto.setCategories(extractList(info.path("categories")));
            dto.setThumbnailUrl(info.path("imageLinks").path("thumbnail").asText(""));
            return dto;
        }
        return null;
    }

    private List<String> extractList(JsonNode node) {
        List<String> list = new ArrayList<>();
        if (node != null && node.isArray()) {
            node.forEach(el -> list.add(el.asText()));
        }
        return list;
    }
}
