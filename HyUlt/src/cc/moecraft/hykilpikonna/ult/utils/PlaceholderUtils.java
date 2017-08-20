package cc.moecraft.hykilpikonna.ult.utils;

import cc.moecraft.hykilpikonna.ult.HyPluginsDownloadLink;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 此类由 Hykilpikonna 在 2017/07/08 创建!
 * Created by Hykilpikonna on 2017/07/08!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class PlaceholderUtils
{
    public static ArrayList<String> replaceTabPlaceholders(ArrayList<String> string)
    {
        for (int i = 0; i < string.size(); i++)
        {
            String oneString = string.get(i);
            if (oneString.contains("%ONLINE_PLAYERS%")) replaceOnlinePlayers(oneString, string, i);
            if (oneString.contains("%HYPLUGINS%")) replaceHyPlugins(oneString, string, i);
        }
        return string;
    }

    private static void replaceOnlinePlayers(String oneString, ArrayList<String> string, int i)
    {
        string.remove(i);
        ArrayList<String> frontAndBack = ArrayUtils.stringArrayToArrayList(oneString.split("%ONLINE_PLAYERS%"));
        if (frontAndBack.size() < 2)
        {
            frontAndBack.add("");
            frontAndBack.add("");
        }
        for (Player player : Bukkit.getOnlinePlayers())
        {
            string.add(i, ChatColor.YELLOW + frontAndBack.get(0) + player.getName() + frontAndBack.get(1) + ChatColor.RESET);
        }
    }

    private static void replaceHyPlugins(String oneString, ArrayList<String> string, int i)
    {
        //TODO: Finish This
        string.remove(i);
        ArrayList<String> frontAndBack = ArrayUtils.stringArrayToArrayList(oneString.split("%HYPLUGINS%"));
        if (frontAndBack.size() < 2)
        {
            frontAndBack.add("");
            frontAndBack.add("");
        }
        for (String name : HyPluginsDownloadLink.getNameList())
        {
            string.add(i, ChatColor.YELLOW + frontAndBack.get(0) + name + frontAndBack.get(1) + ChatColor.RESET);
        }
    }

    public static String replacePlayerPlaceholder(Player player, String input)
    {
        return input.replace("%PLAYER%", player.getName());
    }public static String calculateAndReplaceRandom(String string)
{
    try
    {
        return String.valueOf(calculate(replaceRandomVariable(string)));
    }
    catch (ScriptException e)
    {
        e.printStackTrace();
        return "ERROR";
    }
}

    private static Pattern expressionPattern = Pattern.compile("%c:+(.*)+/c%");

    public static String calculate(String original) throws ScriptException
    {
        try
        {
            Matcher matcher = expressionPattern.matcher(original);
            if (matcher.find())
            {
                Object result = eval(matcher.group().replace("%c:", "").replace("/c%", ""));
                return calculate(matcher.replaceFirst(result.toString()));
            }
            else return original;
        } catch (Exception e) { e.printStackTrace(); return "ERROR"; }
    }

    private static Pattern intRandomPattern = Pattern.compile("%r:+[-0-9,.]+/r%");
    private static Pattern doubleRandomPattern = Pattern.compile("%rd:+[-0-9,.]+/rd%");

    public static String replaceRandomVariable(String original)
    {
        try
        {
            Matcher matcher = intRandomPattern.matcher(original);
            if (matcher.find())
            {
                String maxAndMin = matcher.group().replace("%r:", "").replace("/r%", "");
                int min = Integer.parseInt(maxAndMin.split(",")[0]);
                int max = Integer.parseInt(maxAndMin.split(",")[1]);
                int result = getRandom(min, max);
                return replaceRandomVariable(matcher.replaceFirst(String.valueOf(result)));
            }
            else if ((matcher = doubleRandomPattern.matcher(original)).find())
            {
                String maxAndMin = matcher.group().replace("%rd:", "").replace("/rd%", "");
                double min = Double.parseDouble(maxAndMin.split(",")[0]);
                double max = Double.parseDouble(maxAndMin.split(",")[1]);
                double result = getRandomDouble(min, max);
                return replaceRandomVariable(matcher.replaceFirst(String.valueOf(result)));
            }
            else return original;
        } catch (Exception e) { e.printStackTrace(); return "ERROR"; }
    }

    public static int getRandom(int min, int max)
    {
        return min + new Random().nextInt(max - min);
    }

    public static double getRandomDouble(double min, double max)
    {
        return min + (max - min) * new Random().nextDouble();
    }

    public static double eval(final String str)
    {
        return new Object()
        {
            int pos = -1, ch;

            void nextChar()
            {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat)
            {
                while (ch == ' ') nextChar();
                if (ch == charToEat)
                {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse()
            {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression()
            {
                double x = parseTerm();
                for (;;)
                {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm()
            {
                double x = parseFactor();
                for (;;)
                {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor()
            {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('('))
                { // parentheses
                    x = parseExpression();
                    eat(')');
                }
                else if ((ch >= '0' && ch <= '9') || ch == '.')
                { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                }
                /*else if (ch >= 'a' && ch <= 'z')
                { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                }*/
                else throw new RuntimeException("Unexpected: " + (char)ch);

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}
