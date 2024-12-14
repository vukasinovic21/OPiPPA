package fin.domaci1.controllers;

import fin.domaci1.dtos.LoginDto;
import fin.domaci1.dtos.RegisterDto;
import fin.domaci1.models.Korisnik;
import fin.domaci1.services.KorisnikServis;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.security.NoSuchAlgorithmException;

@Path("/korisnik")
public class KorisnikController
{
    private final KorisnikServis korisnikServis = KorisnikServis.getInstance();

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void register(RegisterDto registerDto) throws Exception, NoSuchAlgorithmException
    {
        korisnikServis.registerUser(registerDto);
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik login(LoginDto loginDto) throws Exception, NoSuchAlgorithmException
    {
        return korisnikServis.login(loginDto);
    }

}
