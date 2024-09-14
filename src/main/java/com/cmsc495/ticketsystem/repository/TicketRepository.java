/* ITMS - A CMSC 495 Project
 * Group 2
 * 07 SEP 24
 * This is the TicketRepository interface.
 * This interface extends JpaRepository and provides CRUD operations for the TicketModel entity.
 * It allows interaction with the database to perform actions such as retrieving, saving, updating,
 * and deleting ticket records by leveraging Spring Data JPA's built-in methods.
 */
package com.cmsc495.ticketsystem.repository;

import com.cmsc495.ticketsystem.model.TicketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<TicketModel, Long> {
}
