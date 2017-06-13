#pragma once
#include "IPawn.h"

class IPawnController
{
public:
	IPawnController();
	virtual ~IPawnController();

	virtual void Update(const float &deltaTime) = 0;

	inline bool isPossessed() const { return m_owner != nullptr; }

	/*	Klasa pionka jest zobowi¹zana do ustawienia
	siebie jako w³aœciciela kontrolera (metod¹ Possess)*/

	friend class IPawn;// ???? TODO: Sprawdziæ znaczenie tej linijki

private:
	/*	Ustawia w³aœciciela tego kontrolera.
	Kontroler mo¿na przypisaæ tylko jednorazowo.
	Jeœli uda³o siê przypisaæ kontroler do pionka to zwraca true.
	*/
	bool Possess(IPawn* owner);

protected:
	IPawn* m_owner; // w³asciciel kontrolera
};

