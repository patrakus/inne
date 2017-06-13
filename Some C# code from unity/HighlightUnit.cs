using UnityEngine;

public class HighlightUnit : MonoBehaviour {

    public GameObject selector;
    public UnitStatus unitStatus;

    bool isHover = false;

    // Use this for initialization
    void Start ()
    {
        if (!selector)
            Debug.LogError("No reference for selector");

        if (selector.activeInHierarchy)
            selector.SetActive(false);
    }

    void Update()
    {
        if (unitStatus.Selected || isHover)
            selector.SetActive(true);
        else if (!unitStatus.Selected)
            selector.SetActive(false);
    }

    void OnMouseOver()
    {
        isHover = true;
    }


    void OnMouseExit()
    {
        isHover = false;
    }
}