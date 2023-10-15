package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import java.io.File;

@Path("/pokemon")
public class PokemonController {

    @GET
    @Path("/{id}")
    @Produces("image/png") // Define o tipo de conteúdo da resposta como imagem JPEG
    public Response getPokemonImage(@PathParam("id") String id) {
        try {
            // Crie o caminho para a imagem com base no ID fornecido
            //String imageName = id + ".png";
            String imageDirectory = "src/main/resources/pokemon-images/" + id + ".png";
            File imageFile = new File(imageDirectory); //Paths.get(imageDirectory, imageName);

            // Verifique se a imagem existe
          
            if (imageFile.exists()) {
                // Retorna a imagem como uma resposta
                return Response.ok(imageFile).build();
            } else {
                // Se a imagem não existir, retorne uma resposta 404 (não encontrado)
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            // Lida com erros e retorna uma resposta de erro
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}