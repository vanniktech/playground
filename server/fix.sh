rm -rf "src/main/sqldelight/app/playground/server/database/"
mkdir -p src/main/sqldelight/app/playground/server/database/

cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V1__init.sql src/main/sqldelight/app/playground/server/database/V1__init.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V2__coach_image_nullable.sql src/main/sqldelight/app/playground/server/database/V2__coach_image_nullable.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V3__mandator_name.sql src/main/sqldelight/app/playground/server/database/V3__mandator_name.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V4__becoach_coach_coachee_setup.sql src/main/sqldelight/app/playground/server/database/V4__becoach_coach_coachee_setup.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V5__niklas_more_codes.sql src/main/sqldelight/app/playground/server/database/V5__niklas_more_codes.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V6__event_planned_amount_nullable.sql src/main/sqldelight/app/playground/server/database/V6__event_planned_amount_nullable.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V7__chat_history.sql src/main/sqldelight/app/playground/server/database/V7__chat_history.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V8__james_template.sql src/main/sqldelight/app/playground/server/database/V8__james_template.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V9__james_session.sql src/main/sqldelight/app/playground/server/database/V9__james_session.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V10__becoach_mandator.sql src/main/sqldelight/app/playground/server/database/V10__becoach_mandator.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V11__push.sql src/main/sqldelight/app/playground/server/database/V11__push.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V12__push_mandator.sql src/main/sqldelight/app/playground/server/database/V12__push_mandator.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V13__chat_entry_received_seen.sql src/main/sqldelight/app/playground/server/database/V13__chat_entry_received_seen.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V14__chat_entry_seen_rename.sql src/main/sqldelight/app/playground/server/database/V14__chat_entry_seen_rename.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V15__fabio_rename.sql src/main/sqldelight/app/playground/server/database/V15__fabio_rename.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V16__event_distribution.sql src/main/sqldelight/app/playground/server/database/V16__event_distribution.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V17__google_apple_accounts.sql src/main/sqldelight/app/playground/server/database/V17__google_apple_accounts.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V18__chat_entry_james_ids.sql src/main/sqldelight/app/playground/server/database/V18__chat_entry_james_ids.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V19__proper_longs.sql src/main/sqldelight/app/playground/server/database/V19__proper_longs.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V20__push_token_device_id.sql src/main/sqldelight/app/playground/server/database/V20__push_token_device_id.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V21__coach_tags.sql src/main/sqldelight/app/playground/server/database/V21__coach_tags.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V22__chat_entry_remove_date.sql src/main/sqldelight/app/playground/server/database/V22__chat_entry_remove_date.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V23__push_token_language.sql src/main/sqldelight/app/playground/server/database/V23__push_token_language.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V24__james_version.sql src/main/sqldelight/app/playground/server/database/V24__james_version.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V25__cancel_target.sql src/main/sqldelight/app/playground/server/database/V25__cancel_target.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V26__challenge_cancel_rename.sql src/main/sqldelight/app/playground/server/database/V26__challenge_cancel_rename.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V27__lowercase_email.sql src/main/sqldelight/app/playground/server/database/V27__lowercase_email.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V28__creator_mandator_for_coach_optional.sql src/main/sqldelight/app/playground/server/database/V28__creator_mandator_for_coach_optional.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V29__delete_verification_token.sql src/main/sqldelight/app/playground/server/database/V29__delete_verification_token.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V30__james_template_creator.sql src/main/sqldelight/app/playground/server/database/V30__james_template_creator.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V31__event_note.sql src/main/sqldelight/app/playground/server/database/V31__event_note.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V32__coach_tag_code.sql src/main/sqldelight/app/playground/server/database/V32__coach_tag_code.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V33__empty_accounts.sql src/main/sqldelight/app/playground/server/database/V33__empty_accounts.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V34__coachee_first_login.sql src/main/sqldelight/app/playground/server/database/V34__coachee_first_login.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V35__coachee_first_login_migration.sql src/main/sqldelight/app/playground/server/database/V35__coachee_first_login_migration.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V36__chat_entry_web_socket_queue.sql src/main/sqldelight/app/playground/server/database/V36__chat_entry_web_socket_queue.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V37__chat_entry_web_socket_queue.sql src/main/sqldelight/app/playground/server/database/V37__chat_entry_web_socket_queue.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V38__coachee_first_login_reminder.sql src/main/sqldelight/app/playground/server/database/V38__coachee_first_login_reminder.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V39__challenge_cancelled_reason.sql src/main/sqldelight/app/playground/server/database/V39__challenge_cancelled_reason.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V40__badges_for_coach_and_coachee.sql src/main/sqldelight/app/playground/server/database/V40__badges_for_coach_and_coachee.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V41__badges_for_dev.sql src/main/sqldelight/app/playground/server/database/V41__badges_for_dev.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V42__badges_updated.sql src/main/sqldelight/app/playground/server/database/V42__badges_updated.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V43__badge_screwup.sql src/main/sqldelight/app/playground/server/database/V43__badge_screwup.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V44__badge_update.sql src/main/sqldelight/app/playground/server/database/V44__badge_update.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V45__timestamp.sql src/main/sqldelight/app/playground/server/database/V45__timestamp.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V46__activity.sql src/main/sqldelight/app/playground/server/database/V46__activity.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V47__activity_week_end_date.sql src/main/sqldelight/app/playground/server/database/V47__activity_week_end_date.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V48__sample_account.sql src/main/sqldelight/app/playground/server/database/V48__sample_account.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V49__coach_first_login.sql src/main/sqldelight/app/playground/server/database/V49__coach_first_login.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V50__share_james_template.sql src/main/sqldelight/app/playground/server/database/V50__share_james_template.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V51__deny_coachee_id.sql src/main/sqldelight/app/playground/server/database/V51__deny_coachee_id.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V52__connect_sample_accounts.sql src/main/sqldelight/app/playground/server/database/V52__connect_sample_accounts.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V53__anonymous_registration.sql src/main/sqldelight/app/playground/server/database/V53__anonymous_registration.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V54__uta_verified.sql src/main/sqldelight/app/playground/server/database/V54__uta_verified.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V55__internationalization.sql src/main/sqldelight/app/playground/server/database/V55__internationalization.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V56__fabio_becoach.sql src/main/sqldelight/app/playground/server/database/V56__fabio_becoach.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V57__timezone.sql src/main/sqldelight/app/playground/server/database/V57__timezone.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V58__fabio2_becoach.sql src/main/sqldelight/app/playground/server/database/V58__fabio2_becoach.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V59__james_templates_activities_migration.sql src/main/sqldelight/app/playground/server/database/V59__james_templates_activities_migration.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V60__nuke_challenges.sql src/main/sqldelight/app/playground/server/database/V60__nuke_challenges.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V61__feedback.sql src/main/sqldelight/app/playground/server/database/V61__feedback.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V62__coach_creator_mandator.sql src/main/sqldelight/app/playground/server/database/V62__coach_creator_mandator.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V63__nuke_challenges_for_real.sql src/main/sqldelight/app/playground/server/database/V63__nuke_challenges_for_real.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V64__coachee_feedback.sql src/main/sqldelight/app/playground/server/database/V64__coachee_feedback.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V65__coach_gender.sql src/main/sqldelight/app/playground/server/database/V65__coach_gender.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V66__becoach_mandator_coach.sql src/main/sqldelight/app/playground/server/database/V66__becoach_mandator_coach.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V67__coachee_feedback_recreate.sql src/main/sqldelight/app/playground/server/database/V67__coachee_feedback_recreate.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V68__coachee_creator_id.sql src/main/sqldelight/app/playground/server/database/V68__coachee_creator_id.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V69__feedback_allow_coachee.sql src/main/sqldelight/app/playground/server/database/V69__feedback_allow_coachee.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V70__feedback_coachee_creator_id.sql src/main/sqldelight/app/playground/server/database/V70__feedback_coachee_creator_id.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V71__last_seen.sql src/main/sqldelight/app/playground/server/database/V71__last_seen.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V72__inactive_deletion_date.sql src/main/sqldelight/app/playground/server/database/V72__inactive_deletion_date.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V73__mandator_limitations.sql src/main/sqldelight/app/playground/server/database/V73__mandator_limitations.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V74__belabs_superadmin.sql src/main/sqldelight/app/playground/server/database/V74__belabs_superadmin.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V75__superadmin_push.sql src/main/sqldelight/app/playground/server/database/V75__superadmin_push.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V76__push_tokens_not_null.sql src/main/sqldelight/app/playground/server/database/V76__push_tokens_not_null.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V77__james_template_creator_super_admin.sql src/main/sqldelight/app/playground/server/database/V77__james_template_creator_super_admin.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V78__james_database.sql src/main/sqldelight/app/playground/server/database/V78__james_database.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V79__move_default_mandator.sql src/main/sqldelight/app/playground/server/database/V79__move_default_mandator.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V80__mandator_first_login.sql src/main/sqldelight/app/playground/server/database/V80__mandator_first_login.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V81__super_admin_internationalization.sql src/main/sqldelight/app/playground/server/database/V81__super_admin_internationalization.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V82__super_admin_timezone.sql src/main/sqldelight/app/playground/server/database/V82__super_admin_timezone.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V83__mandator_is_mandator_admin.sql src/main/sqldelight/app/playground/server/database/V83__mandator_is_mandator_admin.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V84__drop_push_token_language.sql src/main/sqldelight/app/playground/server/database/V84__drop_push_token_language.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V85__nullable_language_timezone.sql src/main/sqldelight/app/playground/server/database/V85__nullable_language_timezone.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V86__week_start_date_end_date.sql src/main/sqldelight/app/playground/server/database/V86__week_start_date_end_date.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V87__activity_coach.sql src/main/sqldelight/app/playground/server/database/V87__activity_coach.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V88__mandator_inactive_deletion_date.sql src/main/sqldelight/app/playground/server/database/V88__mandator_inactive_deletion_date.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V89__notification_group.sql src/main/sqldelight/app/playground/server/database/V89__notification_group.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V90__notification_group_nullable.sql src/main/sqldelight/app/playground/server/database/V90__notification_group_nullable.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V91__feedback_privacy.sql src/main/sqldelight/app/playground/server/database/V91__feedback_privacy.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V92__feedback_delete_anonymous.sql src/main/sqldelight/app/playground/server/database/V92__feedback_delete_anonymous.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V93__coach_tag_code_james_template_id.sql src/main/sqldelight/app/playground/server/database/V93__coach_tag_code_james_template_id.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V94__james_session_source_id.sql src/main/sqldelight/app/playground/server/database/V94__james_session_source_id.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V95__coach_tag_coachee_created.sql src/main/sqldelight/app/playground/server/database/V95__coach_tag_coachee_created.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V96__id_classes_new_id_column.sql src/main/sqldelight/app/playground/server/database/V96__id_classes_new_id_column.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V97__id_classes_new_primary_key.sql src/main/sqldelight/app/playground/server/database/V97__id_classes_new_primary_key.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V98__chat_mail_history.sql src/main/sqldelight/app/playground/server/database/V98__chat_mail_history.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V99__delete_deny.sql src/main/sqldelight/app/playground/server/database/V99__delete_deny.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V100__james_journey.sql src/main/sqldelight/app/playground/server/database/V100__james_journey.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V101__avatar_privacy.sql src/main/sqldelight/app/playground/server/database/V101__avatar_privacy.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V102__avatars.sql src/main/sqldelight/app/playground/server/database/V102__avatars.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V103__feedback_rename.sql src/main/sqldelight/app/playground/server/database/V103__feedback_rename.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V104__feedback_rename_reference.sql src/main/sqldelight/app/playground/server/database/V104__feedback_rename_reference.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V105__james_image.sql src/main/sqldelight/app/playground/server/database/V105__james_image.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V106__redo_image.sql src/main/sqldelight/app/playground/server/database/V106__redo_image.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V107__image_hash_blur.sql src/main/sqldelight/app/playground/server/database/V107__image_hash_blur.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V108__image_james_feedback_answer.sql src/main/sqldelight/app/playground/server/database/V108__image_james_feedback_answer.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V109__image.sql src/main/sqldelight/app/playground/server/database/V109__image.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V110__password.sql src/main/sqldelight/app/playground/server/database/V110__password.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V111__authentication.sql src/main/sqldelight/app/playground/server/database/V111__authentication.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V112__password_non_null.sql src/main/sqldelight/app/playground/server/database/V112__password_non_null.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V113__authentication_app_version.sql src/main/sqldelight/app/playground/server/database/V113__authentication_app_version.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V114__video.sql src/main/sqldelight/app/playground/server/database/V114__video.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V115__login_email.sql src/main/sqldelight/app/playground/server/database/V115__login_email.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V116__nuke_login.sql src/main/sqldelight/app/playground/server/database/V116__nuke_login.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V117__branding.sql src/main/sqldelight/app/playground/server/database/V117__branding.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V118__super_admin_name.sql src/main/sqldelight/app/playground/server/database/V118__super_admin_name.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V119__branding_lottie_animation_coaching.sql src/main/sqldelight/app/playground/server/database/V119__branding_lottie_animation_coaching.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V120__default_coachee.sql src/main/sqldelight/app/playground/server/database/V120__default_coachee.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V121__coach_coachee_note.sql src/main/sqldelight/app/playground/server/database/V121__coach_coachee_note.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V122__mandator_james_journey.sql src/main/sqldelight/app/playground/server/database/V122__mandator_james_journey.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V123__activity_week.sql src/main/sqldelight/app/playground/server/database/V123__activity_week.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V124__coach_tag_code_james_template.sql src/main/sqldelight/app/playground/server/database/V124__coach_tag_code_james_template.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V125__coach_remove_image.sql src/main/sqldelight/app/playground/server/database/V125__coach_remove_image.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V126__branding_lottie_animation_props.sql src/main/sqldelight/app/playground/server/database/V126__branding_lottie_animation_props.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V127__image_video_can_be_shared.sql src/main/sqldelight/app/playground/server/database/V127__image_video_can_be_shared.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V128__file.sql src/main/sqldelight/app/playground/server/database/V128__file.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V129__badges_for_mandator_and_super_admin.sql src/main/sqldelight/app/playground/server/database/V129__badges_for_mandator_and_super_admin.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V130__james_name.sql src/main/sqldelight/app/playground/server/database/V130__james_name.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V131__james_session_source_id.sql src/main/sqldelight/app/playground/server/database/V131__james_session_source_id.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V132__james_session_source_id_non_null.sql src/main/sqldelight/app/playground/server/database/V132__james_session_source_id_non_null.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V133__remove_default_coachee.sql src/main/sqldelight/app/playground/server/database/V133__remove_default_coachee.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V134__coach_coachee_connected_at.sql src/main/sqldelight/app/playground/server/database/V134__coach_coachee_connected_at.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V135__coach_coachee_connected_at_migration.sql src/main/sqldelight/app/playground/server/database/V135__coach_coachee_connected_at_migration.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V136__fix_ids.sql src/main/sqldelight/app/playground/server/database/V136__fix_ids.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V137__uuid_ids.sql src/main/sqldelight/app/playground/server/database/V137__uuid_ids.sqm
cp /Users/niklas/dev/becoach/becoach-service-app/src/main/resources/db/migration/V138__james_journey_delay.sql src/main/sqldelight/app/playground/server/database/V138__james_journey_delay.sqm

find ./src/main/sqldelight/app/playground/server/database/ -type f -name "*.sqm" -exec sed -i '/^CREATE SCHEMA/d' {} \;
find ./src/main/sqldelight/app/playground/server/database/ -type f -name "*.sqm" -exec sed -i '/^DROP SCHEMA/d' {} \;
find ./src/main/sqldelight/app/playground/server/database/ -type f -name "*.sqm" -exec sed -i '/SET SCHEMA/d' {} \;
find ./src/main/sqldelight/app/playground/server/database/ -type f -name "*.sqm" -exec sed -i 's/ chat\./ /g' {} \;
find ./src/main/sqldelight/app/playground/server/database/ -type f -name "*.sqm" -exec sed -i 's/ app\./ /g' {} \;
find ./src/main/sqldelight/app/playground/server/database/ -type f -name "*.sqm" -exec sed -i 's/ james\./ /g' {} \;
find ./src/main/sqldelight/app/playground/server/database/ -type f -name "*.sqm" -exec sed -i 's/ feedback\./ /g' {} \;

# https://github.com/sqldelight/sqldelight/issues/6108
echo "" > src/main/sqldelight/app/playground/server/database/V45__timestamp.sqm

# https://github.com/sqldelight/sqldelight/issues/6109
echo "" > src/main/sqldelight/app/playground/server/database/V59__james_templates_activities_migration.sqm

# https://github.com/sqldelight/sqldelight/issues/6110
echo "" > src/main/sqldelight/app/playground/server/database/V84__drop_push_token_language.sqm

# It really does not exist!

.././gradlew generateMainQueryWrapperInterface
