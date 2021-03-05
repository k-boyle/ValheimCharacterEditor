package com.github.kboyle.valheimcharactereditor.commands.modules;

import com.github.kboyle.valheimcharactereditor.commands.ValheimContext;
import com.github.kboyle.valheimcharactereditor.services.CharacterManagementService;
import kboyle.oktane.core.module.CommandModuleBase;
import kboyle.oktane.core.module.annotations.ModuleDescription;

@ModuleDescription(
    name = "Character Management"
)
public class CharacterManagement extends CommandModuleBase<ValheimContext> {
    private final CharacterManagementService characterManagementService;

    public CharacterManagement(CharacterManagementService characterManagementService) {
        this.characterManagementService = characterManagementService;
    }
}
