/*
 * Copyright (c) 2022-2023. Gavin Pease and contributors.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
 * OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package com.peasenet.main

import com.peasenet.mods.Mod
import com.peasenet.mods.gui.ModGui
import com.peasenet.mods.gui.ModGuiSettings
import com.peasenet.mods.misc.ModCommands

/**
 * A placeholder class that initializes all the mods, and contains a few methods for adding/getting to that list.
 *
 * @author gt3ch1
 * @version 03-02-2023
 */
class Mods {

    init {
        ModGui()
        ModGuiSettings()
    }

    companion object {
        private val modMap = HashMap<String, Mod>()

        @JvmStatic
        val mods: ArrayList<Mod>
            /**
             * Gets the list of mods.
             *
             * @return The list of mods.
             */
            get() {
                // get all values from the map and add them to the list
                val values: Collection<Mod> = modMap.values
                // sort the list by name
                val list = ArrayList(values)
                list.sortWith(Comparator.comparing(Mod::name))
                return list
            }

        /**
         * Gets a mod from the given name.
         *
         * @param name - The name of the mod
         * @return The mod with the given name.
         */
        @JvmStatic
        fun getMod(name: String): Mod? {
            if (modMap[name] == null)
                return null;
            return modMap[name]!!
        }

        @JvmStatic
        fun isActive(name: String): Boolean {
            val mod: Mod = getMod(name) ?: return false
            return mod.isActive
        }

        /**
         * Adds a mod to the mod hashmap using the mod chat command as the key, and the mod as the value.
         *
         * @param m - The mod to add.
         */
        fun addMod(m: Mod) {
            modMap[m.chatCommand] = m
        }
    }
}