#include "IPawnController.h"



IPawnController::IPawnController() : m_owner(nullptr)
{
}


IPawnController::~IPawnController()
{
}

bool IPawnController::Possess(IPawn * owner)
{
	if (!m_owner)
	{
		m_owner = owner;
		return true;
	}
	return false;
}
