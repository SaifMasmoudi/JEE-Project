package tn.micro.service.cloud.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();
    private static final Logger logger = LoggerFactory.getLogger(CustomErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        // Log pour déboguer la réponse d'erreur
        logger.error("Feign error for method {}: Status = {}, Body = {}", methodKey, response.status(), response.body());

        if (response.status() == 400) {
            return new AdressBadRequestException("Requête incorrecte");
        }
        // Vous pouvez gérer d'autres codes de statut ici si nécessaire

        // Retour par défaut pour d'autres erreurs
        return defaultErrorDecoder.decode(methodKey, response);
    }
}
