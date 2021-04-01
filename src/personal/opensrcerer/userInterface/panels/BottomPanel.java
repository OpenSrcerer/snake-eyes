/*
 * Made for the Final Project in CS106, due April 1st 2021. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2021 Daniel Stefani / OpenSrcerer
 */

package personal.opensrcerer.userInterface.panels;

import personal.opensrcerer.userInterface.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static personal.opensrcerer.userInterface.MainWindow.isMute;
import static personal.opensrcerer.userInterface.panels.PanelComponents.*;

/**
 * The JPanel that goes on the bottom of every window instance.
 * Features volume controls.
 */
public class BottomPanel extends JPanel {

    /**
     * Represents the global position of the slider.
     */
    private static int sliderPosition = 100;

    /**
     * The JSlider that controls the music volume.
     */
    private final JSlider slider;

    /**
     * The Speaker button that controls the music being mute/unmute.
     */
    private final JLabel speakerButton;

    /**
     * Create a new BottomPanel.
     */
    public BottomPanel() {
        super(new BorderLayout());
        setBackground(discordGrayer);

        JPanel musicPanel = getJPanel();

        slider = getSlider(e -> {
            JSlider source = ((JSlider) e.getSource());
            if (source.getValueIsAdjusting()) {
                return;
            }
            sliderPosition = source.getValue();
            float volume = source.getValue() / 100f;
            MainWindow.setVolume(volume);
            updateSpeaker(volume);
        });

        speakerButton = new JLabel(imagesList[7]); // Get unmute speaker
        speakerButton.setBackground(discordGrayer);
        speakerButton.addMouseListener(getAdapter());

        musicPanel.add(slider);
        musicPanel.add(speakerButton);

        add(musicPanel, BorderLayout.EAST);
        add(getLabel("  v0.0.1", descriptionFont), BorderLayout.WEST);

        refresh();
    }

    /**
     * Update the speaker to mute if volume is 0, unmute otherwise.
     * @param volume Given volume to adjust with.
     */
    private void updateSpeaker(float volume) {
        if (volume == 0f) {
            speakerButton.setIcon(imagesList[8]); // Set mute speaker
        } else if (volume > 0f) {
            speakerButton.setIcon(imagesList[7]); // Set unmute speaker
        }
    }

    /**
     * @return A MouseAdapter event listener for the speaker button.
     */
    public MouseAdapter getAdapter() {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                MainWindow.getWindowPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                MainWindow.getWindowPane().setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseClicked(MouseEvent evt) {
                if (isMute()) {
                    slider.setValue(25);
                    MainWindow.setVolume(0.25f);
                    updateSpeaker(0.25f);
                } else {
                    slider.setValue(0);
                    MainWindow.setVolume(0f);
                    updateSpeaker(0f);
                }
            }
        };
    }

    /**
     * Refresh the values for the slider and button in this BottomPanel.
     */
    public void refresh() {
        slider.setValue(sliderPosition);
        if (isMute()) {
            speakerButton.setIcon(imagesList[8]);
        } else {
            speakerButton.setIcon(imagesList[7]);
        }
    }
}
