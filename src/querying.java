/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yetItCompiles
 */
import java.io.IOException;
import java.nio.file.Paths;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
public class querying {
    public static void main(String[] args) throws IOException, ParseException {
        String indexDirectory = "C:\\ghazal";
        StandardAnalyzer analyzer = new StandardAnalyzer();
        FSDirectory index = FSDirectory.open(Paths.get(indexDirectory));
     
        String queryStr = "पर मौक़ूफ़";
        Query q = new QueryParser("content", analyzer).parse(queryStr);
        
        int hitsPerPage = 10;
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;
        
        System.out.println("Found "+ hits.length + " hits");
        for(int i = 0; i<hits.length; ++i){
            int docID = hits[i].doc;
            Document d = searcher.doc(docID);
            System.out.println((i+1) + ". Shayar :" + d.get("shayar") + " Title :" + d.get("title"));
        }
        reader.close();
    }
}