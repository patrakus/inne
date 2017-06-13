using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class HealthComponent : MonoBehaviour {

    Texture2D foregroundTexture;
    Texture2D backgroundTexture;

    public UnitStatus unitStatus;

    const int HEALTH = 100;
    int currentHealth = HEALTH;

    // Use this for initialization
    void Start () {
        foregroundTexture = new Texture2D(1, 1);
        foregroundTexture.SetPixel(0, 0, Color.green);
        foregroundTexture.Apply();

        backgroundTexture = new Texture2D(1, 1);
        backgroundTexture.SetPixel(0, 0, Color.black);
        backgroundTexture.Apply();
    }
	
	// Update is called once per frame
	void Update ()
    {
        

	}

    void OnGUI()
    {

        if (unitStatus.Selected)
        {
            Vector3 unitPos = Camera.main.WorldToScreenPoint(this.transform.position);

            GUI.DrawTexture(new Rect(unitPos.x - HEALTH / 2.0f, Screen.height - unitPos.y - 5 / 2.0f, HEALTH, 5), backgroundTexture, ScaleMode.StretchToFill);
            GUI.DrawTexture(new Rect(unitPos.x - HEALTH / 2.0f, Screen.height - unitPos.y - 5 / 2.0f, currentHealth, 5), foregroundTexture, ScaleMode.StretchToFill);
        }
            
    }

    public int Healt
    {
        get
        {
            return currentHealth;
        }
        set
        {

            if (value < 0)
                currentHealth = 0;
            else if (value > HEALTH)
                currentHealth = HEALTH;
            else
                currentHealth = value;
        }
    }
}
