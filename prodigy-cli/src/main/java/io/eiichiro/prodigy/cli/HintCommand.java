/*
 * Copyright (C) 2019-present Eiichiro Uchiumi and the Prodigy Authors. 
 * All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.eiichiro.prodigy.cli;

import java.io.IOException;
import java.io.PrintWriter;

import org.eiichiro.ash.Command;
import org.eiichiro.ash.Line;
import org.eiichiro.ash.Shell;
import org.eiichiro.ash.Usage;

public class HintCommand implements Command {

    private final Shell shell;

    public HintCommand(Shell shell) {
        this.shell = shell;
    }

    @Override
    public String name() {
        return "hint";
    }

    @Override
    public Usage usage() {
        return new Usage("hint");
    }

    @Override
    public void run(Line line) throws Exception {
		try {
            shell.console().reader().printColumns(shell.commands().keySet());
            shell.console().reader().flush();
		} catch (IOException e) {
			e.printStackTrace(new PrintWriter(shell.console().reader().getOutput()));
		}
    }

}
