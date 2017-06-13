using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class SelectionAreaManager : MonoBehaviour {

    
    void OnGUI()
    {
        // Left example
        Utils.DrawScreenRectBorder(new Rect(32, 32, 256, 128), 2, Color.green);
        // Right example
        Utils.DrawScreenRect(new Rect(320, 32, 256, 128), new Color(0.0f, 0.0f, 8.0f, 0.25f));
        Utils.DrawScreenRectBorder(new Rect(320, 32, 256, 128), 2, new Color(0.0f, 0.0f, 8.0f));

    }
}
