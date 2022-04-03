package com.icai.practicas.controller;

import com.icai.practicas.controller.ProcessController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcessControllerTest {
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void given_app_when_login_using_right_credencials_then_Ok() throws Exception{
        
        //Le pasamos el puerto
        String address = "http://localhost:"+port+"/api/v1/process-step1";

        
        ProcessController.DataRequest dataPrueba = new ProcessController.DataRequest("Blanca de Pedro", "52060398J", "646513445");
        //Request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(dataPrueba, headers);
        //Response
        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);
		
        then(result.getBody()).isEqualTo("{\"result\":\"OK\"}");
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

    @Test
    public void given_app_when_login_using_right_credencials_then_ko() throws Exception{
        
        //Le pasamos el puerto
        String address = "http://localhost:"+port+"/api/v1/process-step1";

        ProcessController.DataRequest dataErrorDNI = new ProcessController.DataRequest("Blanca de Pedro", "123456789", "646513445");
        ProcessController.DataRequest dataErrorTelf = new ProcessController.DataRequest("Blanca de Pedro", "52060398J", "AAA513445");
        //Request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<ProcessController.DataRequest> requestErrorDNI = new HttpEntity<>(dataErrorDNI, headers);
        HttpEntity<ProcessController.DataRequest> requestErrorTelf = new HttpEntity<>(dataErrorTelf, headers);
        //Response
        
		ResponseEntity<String> resultErrorDNI = this.restTemplate.postForEntity(address, requestErrorDNI, String.class);
        ResponseEntity<String> resulttErrorTelf = this.restTemplate.postForEntity(address, requestErrorTelf, String.class);
        
        
        then(resultErrorDNI.getBody()).isEqualTo("{\"result\":\"KO\"}");
        then(resulttErrorTelf.getBody()).isEqualTo("{\"result\":\"KO\"}");
        
        then(resultErrorDNI.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(resulttErrorTelf.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void given_app_when_login_using_right_credentials_then_ok_legacy() throws Exception{
            
            //Le pasamos el puerto
            String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";

            //Los datos introducidos son correctos
            MultiValueMap<String, String> datosCorrectos = new LinkedMultiValueMap<>();
            datosCorrectos.add("fullName", "Blanca de Pedro");
            datosCorrectos.add("dni", "52060398J");
            datosCorrectos.add("telefono", "646513445");
            
            //Request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> requestCorrecto = new HttpEntity<>(datosCorrectos, headers);
             //Response
            ResponseEntity<String> resultCorrecto = this.restTemplate.postForEntity(address, requestCorrecto, String.class);
            
            then(resultCorrecto.getBody()).contains("Muchas gracias por enviar los datos");

            then(resultCorrecto.getStatusCode()).isEqualTo(HttpStatus.OK);
            
    }

    @Test
    public void given_app_when_login_using_right_credentials_then_ko_legacy() throws Exception{
            
            //Le pasamos el puerto
            String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";


            //El nombre introducido es incorrecto
            MultiValueMap<String, String> datosNombreIncorrecto = new LinkedMultiValueMap<>();
            datosNombreIncorrecto.add("fullName", "Bl@nc@ de Pedr@");
            datosNombreIncorrecto.add("dni", "52060398J");
            datosNombreIncorrecto.add("telefono", "646513445");

            //El vacío
            MultiValueMap<String, String> datosVacio = new LinkedMultiValueMap<>();
            

            //El DNI introducido es incorrecto
            MultiValueMap<String, String> datosDNIIncorrecto = new LinkedMultiValueMap<>();
            datosDNIIncorrecto.add("fullName", "Blanca de Pedro");
            datosDNIIncorrecto.add("dni", "123456789");
            datosDNIIncorrecto.add("telefono", "646513445");

            //El número introducido es incorrecto
            MultiValueMap<String, String> datosNumeroIncorrecto = new LinkedMultiValueMap<>();
            datosNumeroIncorrecto.add("fullName", "Blanca de Pedro");
            datosNumeroIncorrecto.add("dni", "52060398J");
            datosNumeroIncorrecto.add("telefono", "AAAAA");

            //Request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> requestErrorNombre = new HttpEntity<>(datosNombreIncorrecto, headers);
            HttpEntity<MultiValueMap<String, String>> requestVacio = new HttpEntity<>(datosVacio, headers);
            HttpEntity<MultiValueMap<String, String>> requestErrorDNI = new HttpEntity<>(datosDNIIncorrecto, headers);
            HttpEntity<MultiValueMap<String, String>> requestErrorTelefono = new HttpEntity<>(datosNumeroIncorrecto, headers);
            //Response
            ResponseEntity<String> resultErrorNombre = this.restTemplate.postForEntity(address, requestErrorNombre, String.class);
            ResponseEntity<String> resultVacio = this.restTemplate.postForEntity(address, requestVacio, String.class);
            ResponseEntity<String> resultErrorDNI = this.restTemplate.postForEntity(address, requestErrorDNI, String.class);
            ResponseEntity<String> resultErrorTelefono = this.restTemplate.postForEntity(address, requestErrorTelefono, String.class);
            
            then(resultErrorDNI.getBody()).contains("Hemos tenido un problema con su solicitud");
            then(resultErrorTelefono.getBody()).contains("Hemos tenido un problema con su solicitud");
            
            then(resultErrorNombre.getStatusCode()).isEqualTo(HttpStatus.OK);
            then(resultVacio.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            then(resultErrorDNI.getStatusCode()).isEqualTo(HttpStatus.OK);
            then(resultErrorTelefono.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}

