package ru.dne.constable.queue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.bukkit.entity.LivingEntity;

import org.jetbrains.annotations.NotNull;

/**
 * Single handled event
 */
@Getter @RequiredArgsConstructor
public class Event {

    /*
    Damaged entity
     */
    private final @NotNull LivingEntity damaged;

    /*
    Damager entity
     */
    private final @NotNull LivingEntity damager;

    /*
    Damage
     */
    private final double damage;

}
