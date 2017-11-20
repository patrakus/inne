using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class GameHUD : MonoBehaviour
{
    [Header("Canvas")]
    public GameObject canvasCode;
    public GameObject canvasToolbar;

    [Header("Panels")]
    public GameObject panelMenu;
    public GameObject panelInfo;
    public GameObject panelParts;

    [Header("Toolbar")]
    public Slider slidgerSpeed;

    private float timeScale
    {
        get { return Time.timeScale; }
        set { Time.timeScale = value; }
    }

    public void OpenMenu()
    {
        timeScale = 0;
        panelMenu.SetActive(true);
    }

    public void CloseMenu()
    {
        ChangeGameSpeed();
        panelMenu.SetActive(false);
    }

    public void ResetLevel()
    {
        timeScale = 1;
        AudioManager.ins.PlaySound();
        string sceneName = SceneManager.GetSceneAt(1).name;
        SceneManager.UnloadSceneAsync(sceneName);
        SceneManager.LoadScene(sceneName, LoadSceneMode.Additive);
    }

    public void BackToMainMenu()
    {
        AudioManager.ins.PlaySound(AudioManager.ins.defaultSound);
        string sceneName = SceneManager.GetSceneAt(1).name;
        SceneManager.UnloadSceneAsync(sceneName);
        SceneManager.LoadScene("Menu_Logged", LoadSceneMode.Additive);
    }

    public void AcceptTask()
    {
        panelInfo.SetActive(false);
        canvasCode.SetActive(true);
        canvasToolbar.SetActive(true);
    }

    /// <summary>
    /// Zmiana prędkości rozgrywki - na przesuwanie paska SPEED w trakcie gry
    /// </summary>
    public void ChangeGameSpeed()
    {
        timeScale = slidgerSpeed.value;
    }

    /// <summary>
    /// Metody stworzone na potrzeby przycisku Play i Stop
    /// Dzięki dwóm metodom poniżej jest możliwość wyłączenia jak i włączenia
    /// okna odpowiedzialnego za tworzenie kodu w grze
    /// </summary>

    public void DeactivePanelParts()
    {
        panelParts.SetActive(false);
    }

    public void ActivePanelParts()
    {
        panelParts.SetActive(true);
    }
}
