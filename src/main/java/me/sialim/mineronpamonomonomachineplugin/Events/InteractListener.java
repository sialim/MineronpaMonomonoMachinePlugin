package me.sialim.mineronpamonomonomachineplugin.Events;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InteractListener implements Listener {
    private final Economy economy;

    public InteractListener(Economy economy) {
        this.economy = economy;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.STONE_BUTTON) {
            if (isButton(e.getClickedBlock().getLocation())) {
                double cost = 10.0;
                if (economy.getBalance(e.getPlayer()) >= cost) {
                    economy.withdrawPlayer(e.getPlayer(), cost);
                    int randomIndex = new Random().nextInt(getCustomItemList().size());
                    ItemStack randomItem = getCustomItemList().get(randomIndex);
                    e.getPlayer().getInventory().addItem(randomItem);
                    e.getPlayer().playSound(e.getPlayer(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 10, 1);
                } else {
                    e.getPlayer().sendMessage("No money :(");
                }
            }
        }else if(e.getClickedBlock() !=null && e.getClickedBlock().getType() == Material.LEVER)
        {
            if(isCasinoLever(e.getClickedBlock().getLocation()))
            {
                double fee=5.0;
                if (economy.getBalance(e.getPlayer()) >= fee) {
                    economy.withdrawPlayer(e.getPlayer(), fee);
                    int randomInt = new Random().nextInt(10);
                    gambleGame(e.getPlayer(), randomInt);
                } else {
                    e.getPlayer().sendMessage("You don't have $5! (Balance: "+economy.getBalance(e.getPlayer()) + " )");
                }
            }
        }
        if (e.getItem()!=null&&e.getItem().getType()==Material.CROSSBOW) e.setCancelled(true);
    }
    private void gambleGame(Player p, int i)
    {
        switch(i)
        {
            case 1:
                economy.depositPlayer(p, 3);
                p.playSound(p, Sound.BLOCK_AMETHYST_BLOCK_CHIME, 10, 1);
                p.sendMessage("You earned "+3+" in your balance! New balance: " + economy.getBalance(p));
                break;
            case 2:
                economy.depositPlayer(p, 3);
                p.playSound(p, Sound.BLOCK_AMETHYST_BLOCK_CHIME, 10, 1);
                p.sendMessage("You earned "+3+" in your balance! New balance: " + economy.getBalance(p));
                break;
            case 3:
                economy.depositPlayer(p, 5);
                p.playSound(p, Sound.BLOCK_AMETHYST_BLOCK_CHIME, 10, 1);
                p.sendMessage("You earned "+5+" in your balance! New balance: " + economy.getBalance(p));
                break;
            case 4:
                economy.depositPlayer(p, 9);
                p.playSound(p, Sound.BLOCK_AMETHYST_BLOCK_CHIME, 10, 1);
                p.sendMessage("You earned "+9+" in your balance! New balance: " + economy.getBalance(p));
                break;
            case 5:
                economy.depositPlayer(p, 10);
                p.playSound(p, Sound.BLOCK_AMETHYST_BLOCK_CHIME, 10, 1);
                p.sendMessage("You earned "+10+" in your balance! New balance: " + economy.getBalance(p));
                break;
            case 6:
                economy.depositPlayer(p, 4);
                p.playSound(p, Sound.BLOCK_AMETHYST_BLOCK_CHIME, 10, 1);
                p.sendMessage("You earned "+4+" in your balance! New balance: " + economy.getBalance(p));
                break;
            case 7:
                economy.depositPlayer(p, 1);
                p.playSound(p, Sound.BLOCK_AMETHYST_BLOCK_CHIME, 10, 1);
                p.sendMessage("You earned "+1+" in your balance! New balance: " + economy.getBalance(p));
                break;
            case 8:
                economy.depositPlayer(p, 2);
                p.playSound(p, Sound.BLOCK_AMETHYST_BLOCK_CHIME, 10, 1);
                p.sendMessage("You earned "+2+" in your balance! New balance: " + economy.getBalance(p));
                break;
            case 9:
                economy.depositPlayer(p, 4);
                p.playSound(p, Sound.BLOCK_AMETHYST_BLOCK_CHIME, 10, 1);
                p.sendMessage("You earned "+4+" in your balance! New balance: " + economy.getBalance(p));
                break;
            case 10:
                economy.depositPlayer(p, 100);
                p.playSound(p, Sound.BLOCK_AMETHYST_BLOCK_CHIME, 10, 1);
                p.sendMessage("JACKPOT! $100! New balance: " + economy.getBalance(p));
                break;
            default:
                economy.depositPlayer(p, 4);
                p.playSound(p, Sound.BLOCK_AMETHYST_BLOCK_CHIME, 10, 1);
                p.sendMessage("You earned "+4+" in your balance! New balance: " + economy.getBalance(p));
                break;
        }

    }
    private boolean isButton(Location location)
    {
        if ((location.getWorld().getName().equals("ZOMBIEAPOC"))&&(location.getBlockX() == 270 && location.getBlockZ() == 282 && location.getBlockY() == -52))
            return true;
        return false;
    }

    private boolean isCasinoLever(Location location)
    {
        if ((location.getWorld().getName().equals("ZOMBIEAPOC"))&&(location.getBlockX() == 152 && location.getBlockZ() == 304 && location.getBlockY() == -60)
                ||(location.getWorld().getName().equals("ZOMBIEAPOC"))&&(location.getBlockX() == 154 && location.getBlockZ() == 304 && location.getBlockY() == -60)
                ||(location.getWorld().getName().equals("ZOMBIEAPOC"))&&(location.getBlockX() == 156 && location.getBlockZ() == 304 && location.getBlockY() == -60)
        )
            return true;
        return false;
    }
    public static List<ItemStack> getCustomItemList()
    {
        List<ItemStack> customItems = new ArrayList<>();

        ItemStack clarice = new ItemStack(Material.BONE_MEAL);
        ItemMeta clariceMeta =  clarice.getItemMeta();
        clariceMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Clarice");
        List<String> clariceLore=new ArrayList<>();
        clariceLore.add(ChatColor.GRAY + "A six legged lamb stuffed");
        clariceLore.add(ChatColor.GRAY + "animal with a bright and");
        clariceLore.add(ChatColor.GRAY + "charming soul. The toy");
        clariceLore.add(ChatColor.GRAY + "has a small rattle in it and");
        clariceLore.add(ChatColor.GRAY + "a pendant with the name");
        clariceLore.add(ChatColor.GRAY + "\"Honey Lamb\" on it, and");
        clariceLore.add(ChatColor.GRAY + "\"Trauma toys TM\" on the");
        clariceLore.add(ChatColor.GRAY + "other.");
        clariceMeta.setLore(clariceLore);
        clarice.setItemMeta(clariceMeta);
        customItems.add(clarice);

        ItemStack twilight = new ItemStack(Material.BOOK);
        ItemMeta twilightMeta = twilight.getItemMeta();
        twilightMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Twilight");
        List<String> twilightLore=new ArrayList<>();
        twilightLore.add(ChatColor.GRAY + "A romantic werewolf");
        twilightLore.add(ChatColor.GRAY + "story. Volume 1.");
        twilightMeta.setLore(twilightLore);
        twilight.setItemMeta(twilightMeta);
        customItems.add(twilight);

        ItemStack musicBox = new ItemStack(Material.BROWN_SHULKER_BOX);
        ItemMeta musicMeta = musicBox.getItemMeta();
        musicMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Cupid's Music Box");
        List<String> musicLore=new ArrayList<>();
        musicLore.add(ChatColor.GRAY + "A detailed music box.");
        musicMeta.setLore(musicLore);
        musicBox.setItemMeta(musicMeta);
        customItems.add(musicBox);

        ItemStack tattoo = new ItemStack(Material.CROSSBOW);
        ItemMeta tatMeta = tattoo.getItemMeta();
        tatMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Tattoo Gun");
        List<String> tatLore=new ArrayList<>();
        tatLore.add(ChatColor.GRAY + "Intricate device");
        tatLore.add(ChatColor.GRAY + "capable of creating");
        tatLore.add(ChatColor.GRAY + "great tattoo designs.");
        tatMeta.setLore(tatLore);
        tattoo.setItemMeta(tatMeta);
        customItems.add(tattoo);

        ItemStack ink = new ItemStack(Material.POTION);
        PotionMeta inkMeta = (PotionMeta) ink.getItemMeta();
        inkMeta.setColor(Color.BLACK);
        inkMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Ink");
        List<String> inkLore=new ArrayList<>();
        inkLore.add(ChatColor.GRAY + "Wonder what this");
        inkLore.add(ChatColor.GRAY + "could be used for?");
        inkMeta.setLore(inkLore);
        ink.setItemMeta(inkMeta);
        customItems.add(ink);

        ItemStack net = new ItemStack(Material.BUNDLE);
        ItemMeta netMeta = net.getItemMeta();
        netMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Butterfly Net");
        List<String> netLore=new ArrayList<>();
        netLore.add(ChatColor.GRAY + "A fancy net used");
        netLore.add(ChatColor.GRAY + "for catching");
        netLore.add(ChatColor.GRAY + "butterflies.");
        netMeta.setLore(netLore);
        net.setItemMeta(netMeta);
        customItems.add(net);

        ItemStack bunny = new ItemStack(Material.WHITE_DYE);
        ItemMeta bunnyMeta = bunny.getItemMeta();
        bunnyMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Bunny Plushie");
        List<String> bunnyLore=new ArrayList<>();
        bunnyLore.add(ChatColor.GRAY + "A cute bunny!");
        bunnyLore.add(ChatColor.GRAY + "O.M.G!");
        bunnyMeta.setLore(bunnyLore);
        bunny.setItemMeta(bunnyMeta);
        customItems.add(bunny);

        ItemStack aid = new ItemStack(Material.FEATHER);
        ItemMeta aidMeta = aid.getItemMeta();
        aidMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Band-Aid");
        List<String> aidLore=new ArrayList<>();
        aidLore.add(ChatColor.GRAY + "For treating bruises");
        aidLore.add(ChatColor.GRAY + "and ouchies.");
        aidMeta.setLore(aidLore);
        aid.setItemMeta(aidMeta);
        customItems.add(aid);

        ItemStack beer = new ItemStack(Material.POTION);
        PotionMeta beerMeta = (PotionMeta) beer.getItemMeta();
        beerMeta.setColor(Color.fromARGB(100, 97, 74, 71));
        beerMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Root Beer");
        List<String> beerLore=new ArrayList<>();
        beerLore.add(ChatColor.GRAY + "Yum!");
        beerLore.add(ChatColor.GRAY + "Root beer!");
        beerMeta.setLore(beerLore);
        beer.setItemMeta(beerMeta);
        customItems.add(beer);

        ItemStack note = new ItemStack(Material.BOOK);
        ItemMeta noteMeta = note.getItemMeta();
        noteMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Notepad");
        List<String> noteLore=new ArrayList<>();
        noteLore.add(ChatColor.GRAY + "I wonder what");
        noteLore.add(ChatColor.GRAY + "notes can be ");
        noteLore.add(ChatColor.GRAY + "written here.");
        noteMeta.setLore(noteLore);
        note.setItemMeta(noteMeta);
        customItems.add(note);

        ItemStack sig = new ItemStack(Material.JUNGLE_SAPLING);
        ItemMeta sigMeta = sig.getItemMeta();
        sigMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Sigillaria Sapling");
        List<String> sigLore=new ArrayList<>();
        sigLore.add(ChatColor.GRAY + "A prehistoric tree");
        sigLore.add(ChatColor.GRAY + "that can fit in");
        sigLore.add(ChatColor.GRAY + "your pocket.");
        sigMeta.setLore(sigLore);
        sig.setItemMeta(sigMeta);
        customItems.add(sig);

        ItemStack mush1 = new ItemStack(Material.RED_MUSHROOM);
        ItemMeta mush1Meta = mush1.getItemMeta();
        mush1Meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Mushroom");
        List<String> mush1Lore=new ArrayList<>();
        mush1Lore.add(ChatColor.GRAY + "Yummy?");
        mush1Meta.setLore(mush1Lore);
        mush1.setItemMeta(mush1Meta);
        customItems.add(mush1);

        ItemStack mush2 = new ItemStack(Material.BROWN_MUSHROOM);
        ItemMeta mush2Meta = mush2.getItemMeta();
        mush2Meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Mushroom");
        List<String> mush2Lore=new ArrayList<>();
        mush2Lore.add(ChatColor.GRAY + "Yummy?");
        mush2Meta.setLore(mush2Lore);
        mush2.setItemMeta(mush2Meta);
        customItems.add(mush2);

        ItemStack fert = new ItemStack(Material.BROWN_DYE);
        ItemMeta fertMeta = fert.getItemMeta();
        fertMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "High-Quality Fertilizer");
        List<String> fertLore=new ArrayList<>();
        fertLore.add(ChatColor.GRAY + "Plants love it,");
        fertLore.add(ChatColor.GRAY + "you love it,");
        fertLore.add(ChatColor.GRAY + "why not keep it?");
        fertMeta.setLore(fertLore);
        fert.setItemMeta(fertMeta);
        customItems.add(fert);

        ItemStack rose = new ItemStack(Material.WITHER_ROSE);
        ItemMeta roseMeta = rose.getItemMeta();
        roseMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Dead Rose");
        List<String> roseLore=new ArrayList<>();
        roseLore.add(ChatColor.GRAY + "It's dead.");
        roseMeta.setLore(roseLore);
        rose.setItemMeta(roseMeta);
        customItems.add(rose);

        ItemStack cookie = new ItemStack(Material.COOKIE);
        ItemMeta cookieMeta = cookie.getItemMeta();
        cookieMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Cookie");
        List<String> cookieLore=new ArrayList<>();
        cookieLore.add(ChatColor.GRAY + "Yum!");
        cookieMeta.setLore(cookieLore);
        cookie.setItemMeta(cookieMeta);
        customItems.add(cookie);

        ItemStack buck = new ItemStack(Material.BUCKET);
        ItemMeta buckMeta = buck.getItemMeta();
        buckMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Bucket");
        List<String> buckLore=new ArrayList<>();
        buckLore.add(ChatColor.GRAY + "Memories...");
        buckMeta.setLore(buckLore);
        buck.setItemMeta(buckMeta);
        customItems.add(buck);

        ItemStack dia = new ItemStack(Material.DIAMOND);
        ItemMeta diaMeta = dia.getItemMeta();
        diaMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Diamond");
        List<String> diaLore=new ArrayList<>();
        diaLore.add(ChatColor.GRAY + "So shiny!");
        diaMeta.setLore(diaLore);
        dia.setItemMeta(diaMeta);
        customItems.add(dia);

        return customItems;
    }
}
