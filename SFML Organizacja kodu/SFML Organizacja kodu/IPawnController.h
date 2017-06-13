#pragma once
#include "IPawn.h"

class IPawnController
{
public:
	IPawnController();
	virtual ~IPawnController();

	virtual void Update(const float &deltaTime) = 0;

	inline bool isPossessed() const { return m_owner != nullptr; }

	/*	Klasa pionka jest zobowi�zana do ustawienia
	siebie jako w�a�ciciela kontrolera (metod� Possess)*/

	friend class IPawn;// ???? TODO: Sprawdzi� znaczenie tej linijki

private:
	/*	Ustawia w�a�ciciela tego kontrolera.
	Kontroler mo�na przypisa� tylko jednorazowo.
	Je�li uda�o si� przypisa� kontroler do pionka to zwraca true.
	*/
	bool Possess(IPawn* owner);

protected:
	IPawn* m_owner; // w�asciciel kontrolera
};

