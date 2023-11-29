import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SearchAction extends AnAction {
    @Override
    public void update(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        e.getPresentation().setEnabledAndVisible(project != null);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        String request = Messages.showInputDialog("Type what you want", "StackOverflow Search", Messages.getQuestionIcon());
        if (request == null || request.isEmpty())
            return;

        String encoded = "";
        try {
            encoded = URLEncoder.encode(request, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            Messages.showMessageDialog("Error occurred", "", Messages.getErrorIcon());
        }

        String url_base = "https://stackoverflow.com/search?q=";
        String url = url_base + encoded;

        BrowserUtil.browse(url);
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }
}
