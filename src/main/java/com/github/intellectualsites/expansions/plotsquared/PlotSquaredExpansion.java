package com.github.intellectualsites.expansions.plotsquared;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class PlotSquaredExpansion extends PlaceholderExpansion
{
    private PlotSquaredApiInterface api;
    
    public PlotSquaredExpansion() {
        this.api = null;
    }
    
    public boolean canRegister() {
        return Bukkit.getPluginManager().getPlugin(this.getPlugin()) != null;
    }
    
    public String getAuthor() {
        return "IronApollo";
    }
    
    public String getIdentifier() {
        return "plotsquared";
    }
    
    public String getPlugin() {
        return "PlotSquared";
    }
    
    public String getVersion() {
        return "1.0";
    }
    
    public boolean register() {
        this.api = determineApi();
        return this.api != null && PlaceholderAPI.registerPlaceholderHook(this.getIdentifier(), (PlaceholderHook)this);
    }
    
    public String onPlaceholderRequest(final Player p, final String identifier) {
    	return this.api.onPlaceHolderRequest(p, identifier);
    }
    
    private PlotSquaredApiInterface determineApi() {
    	try {
    		return new PlotSquaredApiNew();
    	}catch(NoClassDefFoundError e) {
    		try {
    			return new PlotSquaredApiOld();
    		}catch(NoClassDefFoundError e1) {
    			return null;
    		}
    	}
    }
}
