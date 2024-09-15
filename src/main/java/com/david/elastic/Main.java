package com.david.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.util.Base64;

public class Main {

    public static final String PRODUCTS = "products";

    public static void main(String[] args) throws IOException {

        // URL and API key or basic authentication
        String SERVER_URL = "http://localhost:9200";
        //String API_KEY = "AAEAAWVsYXN0aWMva2liYW5hL3Rva2VuMTpVWWdkYjB4M1JvV0lOTERULXcyT0d3";

        String userPassword = "elastic:elastic123";
        String basicAuthentication = Base64.getEncoder().encodeToString(userPassword.getBytes());

        // Create the low-level client
        RestClient restClient = RestClient
                .builder(HttpHost.create(SERVER_URL))
                .setDefaultHeaders(new Header[]{
                        //new BasicHeader("Authorization", "ApiKey " + API_KEY)
                        new BasicHeader("Authorization", "Basic " + basicAuthentication)
                })
                .build();

        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper()
        );

        // And create the API client
        ElasticsearchClient esClient = new ElasticsearchClient(transport);

        // Use the client...

        try {
            //createIndex(esClient, PRODUCTS);

            Product product = new Product(1, "City bike", "Great bike", 199);

            //indexDocument(esClient, product);

            //getDocument(esClient, String.valueOf(product.getId()));

            String searchText = "bike";

            //searchDocument(esClient, searchText);

            Product updatedProduct = new Product(1, "City bike", "Great bike", 299);

            //updateDocument(esClient, updatedProduct);

            //deleteDocument(esClient, String.valueOf(product.getId()));

            //deleteIndex(esClient, PRODUCTS);

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }

        // Close the transport, freeing the underlying thread
        transport.close();
    }

    private static void deleteIndex(ElasticsearchClient esClient, String index) throws IOException {

        DeleteIndexResponse deleteIndexResponse = esClient.indices().delete(d -> d
                .index(index)
        );

        System.out.println("Response deleting index: " + deleteIndexResponse.toString());
    }

    private static void deleteDocument(ElasticsearchClient esClient, String id) throws IOException {

        DeleteResponse deleteResponse = esClient.delete(d -> d.index("products").id(id));

        System.out.println("Response deleting document: " + deleteResponse.toString());
    }

    private static void updateDocument(ElasticsearchClient esClient, Product product) throws IOException {

        UpdateResponse<Product> updateResponse = esClient.update(u -> u
                        .index(PRODUCTS)
                        .id(String.valueOf(product.getId()))
                        .doc(product)
                        .docAsUpsert(true),
                Product.class
        );

        System.out.println("Response updating document: " + updateResponse.toString());
    }

    private static void searchDocument(ElasticsearchClient esClient, String searchText) throws IOException {

        SearchResponse<Product> response = esClient.search(s -> s
                        .index(PRODUCTS)
                        .query(q -> q
                                .match(t -> t
                                        .field("name")
                                        .query(searchText)
                                )
                        ),
                Product.class
        );

        System.out.println("Response searching document: " + response.toString());
        System.out.println("Document: " + response.hits().hits().get(0).source().toString());
    }

    private static void getDocument(ElasticsearchClient esClient, String id) throws IOException {

        GetResponse<Product> response = esClient.get(g -> g
                        .index(PRODUCTS)
                        .id(id),
                Product.class
        );

        if (response.found()) {

            Product product = response.source();

            System.out.println("Product: " + product.toString());

        } else {
            System.out.println("Product not found");
        }
    }

    private static void indexDocument(ElasticsearchClient esClient, Product product) throws IOException {

        IndexResponse response = esClient.index(i -> i
                .index(PRODUCTS)
                .id(String.valueOf(product.getId()))
                .document(product)
        );

        System.out.println("Response indexing document: " + response.result().toString());
    }

    private static void createIndex(ElasticsearchClient esClient, String index) throws IOException {

        CreateIndexResponse createIndexResponse = esClient.indices().create(builder -> builder.index(index));

        System.out.println("Response creating index: " + createIndexResponse.toString());
    }
}
