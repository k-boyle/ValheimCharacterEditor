package com.github.kboyle.valheimcharactereditor.services;

import com.github.kboyle.valheimcharactereditor.entities.Character;
import com.github.kboyle.valheimcharactereditor.wrappers.ByteArrayReader;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CharacterManagementService {
    private final Map<String, Character> characters;

    public CharacterManagementService() {
        characters = new HashMap<>();
    }

    public Character load(URI file) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(file));
            Character character = Character.read(new ByteArrayReader(bytes));
            //todo add to map
            return character;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
