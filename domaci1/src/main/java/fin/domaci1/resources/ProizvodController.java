package fin.domaci1.resources;

import fin.domaci1.models.Proizvod;
import fin.domaci1.services.ProizvodServis;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/proizvod")
public class ProizvodController
{
    private final ProizvodServis proizvodServis = ProizvodServis.getInstance();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Proizvod nadjiProizvodPoId(@PathParam("id") int id) throws Exception
    {
        return proizvodServis.nadjiProizvodPoId(id);
    }

    @GET
    @Path("/svi")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pretragaProizvoda() throws Exception
    {
        return Response.ok(proizvodServis.sviProizvodi()).build();
    }
    
    @GET
    @Path("/pretraga")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pretragaProizvoda(
            @QueryParam("donjaGranica") Integer donjaGranica,
            @QueryParam("gornjaGranica") Integer gornjaGranica,
            @QueryParam("vrsta") String vrsta,
            @QueryParam("kljucnaRec") String kljucnaRec,
            @QueryParam("korisnikId") Integer korisnikId) throws Exception
    {
        return Response.ok(proizvodServis.pretragaProizvoda(donjaGranica, gornjaGranica, vrsta, kljucnaRec, korisnikId)).build();
    }
}
