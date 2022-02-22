CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
INSERT INTO sec.user_role (id, insert_date, is_active, saves_user, authority, description, lang_param, form_lang_param, module_lang_param) VALUES ('5e0ccf71-9915-4bc0-ad46-200607517f1c', now(), true, 'kazu', 'ADMIN', 'Sistem Yöneticisi', 'role.lang.param.admin', 'role.lang.param.admin', 'role.lang.param.admin');
INSERT INTO sec.user_account (id, insert_date, is_active, saves_user, name, password, surname, email) VALUES ('309f568c-7f1f-4607-a77c-4e3770effc5e', now(), true, 'system', 'Kazu', '$2a$10$fZ903z4FMpSjxat5s50aqefML.Gf8hk8Rb1LSphtf2sV6q8YMOjdi', 'Team', 'akif.ova@gmail.com');
INSERT INTO sec.user_authorization (id, insert_date, is_active, saves_user, user_role, user_account) VALUES ('4b6174c5-07ad-4bd4-8e6b-b39e8f894f5c', now(), true, 'system', '5e0ccf71-9915-4bc0-ad46-200607517f1c', '309f568c-7f1f-4607-a77c-4e3770effc5e');

INSERT INTO sec.user_role (id, insert_date, is_active, saves_user, authority, description, lang_param, form_lang_param, module_lang_param) VALUES
(uuid_generate_v4(), now(), true, 'kazu', 'USER', 'Kullanıcı İşlemleri', 'role.lang.param.user', 'module.user', 'Kullanıcı Tanımlama Formu'),
(uuid_generate_v4(), now(), true, 'kazu', 'STOCK_CARD', 'Stok Kartı Tanımları', 'role.lang.param.stockCard', 'module.supplyChain', 'form.stockCard.definition'),
(uuid_generate_v4(), now(), true, 'kazu', 'DESIGN_PLAN', 'Tasarım Planı', 'role.lang.param.designPlan', 'module.design', 'form.designPlan.definition'),
(uuid_generate_v4(), now(), true, 'kazu', 'FIRM', 'Firma İşlemleri', 'role.lang.param.firm', 'module.common', 'form.firm.definition'),
(uuid_generate_v4(), now(), true, 'kazu', 'UOM', 'Ölçü Birimi İşlemleri', 'role.lang.param.uom', 'module.common', 'form.uom.definition'),
(uuid_generate_v4(), now(), true, 'kazu', 'BRAND', 'Marka İşlemleri', 'role.lang.param.brand', 'module.common', 'form.brand.definition'),
(uuid_generate_v4(), now(), true, 'kazu', 'YARN', 'İplik Tanım İşlemleri', 'role.lang.param.yarn', 'module.yarn', 'form.yarn.definition'),
(uuid_generate_v4(), now(), true, 'kazu', 'SAMPLE_YARN', 'Numune İplik Tanım İşlemleri', 'module.specimen', 'role.lang.param.specimen.sampleYarn', 'form.sampleYarn.definition'),
(uuid_generate_v4(), now(), true, 'kazu', 'CREEL', 'Cağ Dizilim Formu Tanım İşlemleri', 'module.specimen', 'role.lang.param.specimen.creel', 'form.creel.definition'),
(uuid_generate_v4(), now(), true, 'kazu', 'ARCHIVE_UNUSABLE', 'Deseni Kullanılmayacak Durumuna Getirebilir', 'module.archive', 'role.lang.param.archive.unusable', 'form.archive'),
(uuid_generate_v4(), now(), true, 'kazu', 'ARCHIVE_STOCK', 'Deseni Stoğa Saklandı Durumuna Getirebilir', 'module.archive', 'role.lang.param.archive.stock', 'form.archive'),
(uuid_generate_v4(), now(), true, 'kazu', 'ARCHIVE_RETIRED', 'Deseni Emekli Edebilir', 'module.archive', 'role.lang.param.archive.retired', 'form.archive'),
(uuid_generate_v4(), now(), true, 'kazu', 'ARCHIVE_SAMPLE', 'Deseni Numune Olarak Kaydedebilir', 'module.archive', 'role.lang.param.archive.sample', 'form.archive'),
(uuid_generate_v4(), now(), true, 'kazu', 'ARCHIVE_RD', 'Tasarım Çalışmalarını Kaydedebilir', 'module.archive', 'role.lang.param.archive.rd', 'form.archive'),
(uuid_generate_v4(), now(), true, 'kazu', 'ARCHIVE_WRITE', 'Deseni Arşive Kaydedebilir', 'module.archive', 'role.lang.param.archive.write', 'form.archive'),
(uuid_generate_v4(), now(), true, 'kazu', 'ARCHIVE_READ', 'Deseni Arşivini Görebilir', 'module.archive', 'role.lang.param.archive.read', 'form.archive'),
(uuid_generate_v4(), now(), true, 'kazu', 'ARCHIVE_ADD', 'Desen Ekleyebilir', 'module.archive', 'role.lang.param.archive.addDesign', 'form.archive.addDesign'),
(uuid_generate_v4(), now(), true, 'kazu', 'IMAGE', 'Image Dosyalarını Okuyabilir', 'module.archive', 'role.lang.param.image', 'form.tool'),
(uuid_generate_v4(), now(), true, 'kazu', 'DOWNLOAD_DESIGN', 'Desen Dosyalarını (.bmp) İndirebilir', 'module.archive', 'role.lang.param.archive.image.bmp', 'form.archive'),
(uuid_generate_v4(), now(), true, 'kazu', 'DOWNLOAD_JPG', 'Desen Dosyalarını (.jpg) İndirebilir', 'module.archive', 'role.lang.param.archive.image.jpg', 'form.archive');

INSERT INTO yarn.yarn_type(id, is_active, insert_date, saves_user, version, name) VALUES ('390498d6-4b0c-4b3e-9eeb-6547e44ecd0b', true, now(), 'kazu', 1, 'Hav İpliği');
INSERT INTO yarn.yarn_group(id, is_active, insert_date, saves_user, version, name, yarn_type) VALUES
('ac24de14-543e-4f4b-884e-ed712838a333', true, now(), 'kazu', 1, 'Akrilik', '390498d6-4b0c-4b3e-9eeb-6547e44ecd0b'),
('70e19e30-a038-466c-ae53-76b565a44395', true, now(), 'kazu', 1, 'Polyester', '390498d6-4b0c-4b3e-9eeb-6547e44ecd0b'),
('c82770fa-e9cb-4674-b918-95c7326c1ac9', true, now(), 'kazu', 1, 'Polypropilen', '390498d6-4b0c-4b3e-9eeb-6547e44ecd0b'),
('a58580d2-4404-4e19-a1f9-b57d44bade4f', true, now(), 'kazu', 1, 'Viskon', '390498d6-4b0c-4b3e-9eeb-6547e44ecd0b');

INSERT INTO common.firm(id, is_active, insert_date, saves_user, version, is_customer, is_person_company, name, is_outsourcer, is_supplier, tax_number, tax_office, trade_name, address) VALUES
('f415ad22-3df4-45f6-a1c8-1b62406b0809', true, now(), 'kazu', 1, false, 0, 'Kazu Yazılım', false, true, '00000', 'Tax Office', 'Kazu Yazılım', null);

INSERT INTO common.unit_of_measure(id, is_active, insert_date, saves_user, version, unit, short_name) VALUES
('5bf85b11-fd47-4f60-8a4b-9e5c72e39434', true, now(), 'kazu', 1, 'Kilogram', 'Kg.'),
('06f7c08d-033b-47df-bd0d-58b22e895359', true, now(), 'kazu', 1, 'Litre', 'Lt.'),
('52aad836-6bec-40ac-9c9a-42f88d08144b', true, now(), 'kazu', 1, 'Şişe', 'Şişe'),
('2b04f7b7-72d0-4b4b-b93f-e521d6ad2c97', true, now(), 'kazu', 1, 'Koli', 'Koli'),
('28e15ab0-0c00-463f-afb7-b3f82094c529', true, now(), 'kazu', 1, 'Adet', 'Adt.'),
('8f857fb8-943c-4591-bd72-713160a05932', true, now(), 'kazu', 1, 'MetreKare', 'm²'),
('bc0989f6-d091-4f78-9741-67ecfa588dff', true, now(), 'kazu', 1, 'Dtex', 'Dtex'),
('8f0361a7-d1be-4128-883c-a80d7df26685', true, now(), 'kazu', 1, 'Nm', 'Nm'),
('565a0fa0-13ed-4485-befd-695f8e3281a5', true, now(), 'kazu', 1, 'Ne', 'Ne'),
('7209da82-c226-41c8-9038-8201b926944b', true, now(), 'kazu', 1, 'Denye', 'Denye'),
('ea994ab3-5f31-4b9c-ba66-2b8c014eee44', true, now(), 'kazu', 1, 'Lbs', 'Lbs');