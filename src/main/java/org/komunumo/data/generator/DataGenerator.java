/*
 * Komunumo - Open Source Community Manager
 * Copyright (C) Marcus Fihlon and the individual contributors to Komunumo.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.komunumo.data.generator;

import com.vaadin.flow.spring.annotation.SpringComponent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.komunumo.data.db.enums.SponsorLevel;
import org.komunumo.data.service.AuthService;
import org.komunumo.data.service.EventService;
import org.komunumo.data.service.EventSpeakerService;
import org.komunumo.data.service.MemberService;
import org.komunumo.data.service.SpeakerService;
import org.komunumo.data.service.SponsorService;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(final AuthService authService,
                                      final EventService eventService,
                                      final MemberService memberService,
                                      final SpeakerService speakerService,
                                      final SponsorService sponsorService,
                                      final EventSpeakerService eventSpeakerService) {
        return args -> {
            final var logger = LoggerFactory.getLogger(getClass());

            if (speakerService.get(1L).isEmpty()) {
                logger.info("Generating speaker entities...");

                final var speaker1 = speakerService.newRecord();
                speaker1.setFirstName("John");
                speaker1.setLastName("Doe");
                speakerService.store(speaker1);

                final var speaker2 = speakerService.newRecord();
                speaker2.setFirstName("Jane");
                speaker2.setLastName("Doe");
                speakerService.store(speaker2);
            }

            if (eventService.get(1L).isEmpty()) {
                logger.info("Generating event entities...");

                final var event1 = eventService.newRecord();
                event1.setTitle("Testevent One");
                event1.setDate(LocalDateTime.of(2021, 10, 1, 18, 0, 0));
                event1.setVisible(true);
                eventService.store(event1);

                final var event2 = eventService.newRecord();
                event2.setTitle("Testevent Two");
                event2.setDate(LocalDateTime.of(2021, 11, 1, 18, 0, 0));
                event2.setVisible(true);
                eventService.store(event2);

                final var event3 = eventService.newRecord();
                event3.setTitle("Testevent Three");
                event3.setDate(LocalDateTime.of(2021, 12, 1, 18, 0, 0));
                event3.setVisible(false);
                eventService.store(event3);

                final var eventSpeaker1 = eventSpeakerService.newRecord();
                eventSpeaker1.setEventId(1L);
                eventSpeaker1.setSpeakerId(1L);
                eventSpeakerService.store(eventSpeaker1);

                final var eventSpeaker2 = eventSpeakerService.newRecord();
                eventSpeaker2.setEventId(2L);
                eventSpeaker2.setSpeakerId(2L);
                eventSpeakerService.store(eventSpeaker2);

                final var eventSpeaker3 = eventSpeakerService.newRecord();
                eventSpeaker3.setEventId(3L);
                eventSpeaker3.setSpeakerId(1L);
                eventSpeakerService.store(eventSpeaker3);

                final var eventSpeaker4 = eventSpeakerService.newRecord();
                eventSpeaker4.setEventId(3L);
                eventSpeaker4.setSpeakerId(2L);
                eventSpeakerService.store(eventSpeaker4);
            }

            if (memberService.get(1L).isEmpty()) {
                logger.info("Generating member entities...");

                final var member1 = memberService.newRecord();
                member1.setFirstName("Marcus");
                member1.setLastName("Fihlon");
                member1.setEmail("marcus@fihlon.ch");
                member1.setAddress("Winkelriedstrasse 25");
                member1.setZipCode("6003");
                member1.setCity("Luzern");
                member1.setState("Luzern");
                member1.setCountry("Schweiz");
                member1.setMemberSince(LocalDateTime.of(2013, 2, 1, 19, 28, 44));
                member1.setAdmin(false);
                member1.setPasswordSalt(authService.createPasswordSalt());
                member1.setPasswordHash(authService.getPasswordHash("user", member1.getPasswordSalt()));
                member1.setActive(true);
                memberService.store(member1);

                final var member2 = memberService.newRecord();
                member2.setFirstName("Marcus");
                member2.setLastName("Fihlon");
                member2.setEmail("marcus@fihlon.swiss");
                member2.setAddress("Winkelriedstrasse 25");
                member2.setZipCode("6003");
                member2.setCity("Luzern");
                member2.setState("Luzern");
                member2.setCountry("Schweiz");
                member2.setMemberSince(LocalDateTime.of(2013, 2, 1, 14, 32, 17));
                member2.setAdmin(true);
                member2.setPasswordSalt(authService.createPasswordSalt());
                member2.setPasswordHash(authService.getPasswordHash("admin", member2.getPasswordSalt()));
                member2.setActive(true);
                memberService.store(member2);
            }

            if (sponsorService.get(1L).isEmpty()) {
                logger.info("Generating sponsor entities...");

                final var sponsor1 = sponsorService.newRecord();
                sponsor1.setName("mimacom ag");
                sponsor1.setUrl("https://www.mimacom.com/");
                sponsor1.setLogo("https://www.jug.ch/images/sponsors/mimacom_platin.jpg");
                sponsor1.setValidFrom(LocalDate.of(2000, 1, 1));
                sponsor1.setValidTo(LocalDate.of(2099, 12, 31));
                sponsor1.setLevel(SponsorLevel.PLATIN);
                sponsorService.store(sponsor1);

                final var sponsor2 = sponsorService.newRecord();
                sponsor2.setName("Netcetera");
                sponsor2.setUrl("https://www.netcetera.com/");
                sponsor2.setLogo("https://www.jug.ch/images/sponsors/netcetera.gif");
                sponsor2.setValidFrom(LocalDate.of(2000, 1, 1));
                sponsor2.setValidTo(LocalDate.of(2099, 12, 31));
                sponsor2.setLevel(SponsorLevel.GOLD);
                sponsorService.store(sponsor2);

                final var sponsor3 = sponsorService.newRecord();
                sponsor3.setName("CSS Versicherung");
                sponsor3.setUrl("https://www.css.ch/");
                sponsor3.setLogo("https://www.jug.ch/images/sponsors/CSS.png");
                sponsor3.setValidFrom(LocalDate.of(2000, 1, 1));
                sponsor3.setValidTo(LocalDate.of(2099, 12, 31));
                sponsor3.setLevel(SponsorLevel.SILBER);
                sponsorService.store(sponsor3);
            }

            logger.info("Demo data ready.");
        };
    }

}
