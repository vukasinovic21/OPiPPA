package fin.domaci1.controllers;

import fin.domaci1.dtos.KupovinaDto;
import fin.domaci1.services.KupovinaServis;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/kupovina")
public class KupovinaController
{

        private final KupovinaServis kupovinaServis = KupovinaServis.getInstance();

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response kupi(KupovinaDto kupovinaDto) throws Exception
        {
            kupovinaServis.kupi(kupovinaDto.getProizvodId(), kupovinaDto.getKorisnikId());
            return Response.ok().build();
        }
}
