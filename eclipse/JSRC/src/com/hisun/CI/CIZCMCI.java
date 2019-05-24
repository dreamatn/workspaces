package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.DC.*;
import com.hisun.EQ.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZCMCI {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm CITBAS_RD;
    DBParm CITLS2_RD;
    brParm CITACAC_BR = new brParm();
    DBParm CITACAC_RD;
    brParm CITACR_BR = new brParm();
    DBParm CITACR_RD;
    brParm CITAGT_BR = new brParm();
    DBParm CITAGT_RD;
    DBParm CITLIMT_RD;
    DBParm CITHIS_RD;
    boolean pgmRtn = false;
    String K_CITIZEN_CARD = "1203010101";
    String K_ALIEN_CARD = "1203021401";
    int WS_CARD_NUM_TOT = 0;
    int WS_CARD_NUM_MCI = 0;
    int WS_CARD_NUM_PCI = 0;
    double WS_LMT = 0;
    int WS_EFF_DT = 0;
    int WS_EXP_DT = 0;
    int WS_NUM = 0;
    CIZCMCI_WS_CARD_LIMIT[] WS_CARD_LIMIT = new CIZCMCI_WS_CARD_LIMIT[10];
    int WS_LIMIT_NUM = 0;
    char WS_JGL_FLG = ' ';
    char WS_CITIZEN_FLG = ' ';
    int WS_I = 0;
    int WS_SEQ = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDCSZFJZ DDCSZFJZ = new DDCSZFJZ();
    CIRBAS CIRBAS = new CIRBAS();
    CIRBAS CIRBASO = new CIRBAS();
    CIRBAS CIRBASN = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRACAC CIRACAC = new CIRACAC();
    CIRAGT CIRAGT = new CIRAGT();
    CIRLIMT CIRLIMT = new CIRLIMT();
    CIRHIS CIRHIS = new CIRHIS();
    CIRHIS CIRHISO = new CIRHIS();
    CIRHIS CIRHISN = new CIRHIS();
    CIRLS2 CIRLS2 = new CIRLS2();
    DCCUCINO DCCUCINO = new DCCUCINO();
    EQCSACT EQCSACT = new EQCSACT();
    BPCFXCUS BPCFXCUS = new BPCFXCUS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    CICPCDL CICPCDL = new CICPCDL();
    DCCIQHLD DCCIQHLD = new DCCIQHLD();
    DDCRFBID DDCRFBID = new DDCRFBID();
    DDRFBID DDRFBID = new DDRFBID();
    int WS_AC_DATE = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICCMCI CICCMCI;
    public CIZCMCI() {
        for (int i=0;i<10;i++) WS_CARD_LIMIT[i] = new CIZCMCI_WS_CARD_LIMIT();
    }
    public void MP(SCCGWA SCCGWA, CICCMCI CICCMCI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICCMCI = CICCMCI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZCMCI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICCMCI.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CHECK_COMB_INFO();
        if (pgmRtn) return;
        B030_COMB_CITACR_INF();
        if (pgmRtn) return;
        B040_COMB_CITACAC_INF();
        if (pgmRtn) return;
        B050_COMB_CITAGT_INF();
        if (pgmRtn) return;
        B070_COMB_DC_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_CHECK_COMB_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICCMCI.DATA.MAIN_CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        if (CIRBAS.CI_TYP != '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CITYP_MUST_BE_P);
        }
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (CIRBAS.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_STSW_IS_HOLD);
        }
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (CIRBAS.STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_IS_DEAD);
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICCMCI.DATA.PRI_CI_NO;
        T000_READ_CITBAS_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        if (CIRBAS.CI_TYP != '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CITYP_MUST_BE_P);
        }
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (CIRBAS.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_STSW_IS_HOLD);
        }
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (CIRBAS.STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_IS_DEAD);
        }
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (CIRBAS.STSW.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_IS_ALREADY_MERGED);
        }
        B020_01_CHECK_AGTLVL_INF();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRHIS);
        CIRHIS.KEY.FMT_ID = "CITBAS";
        CIRHIS.KEY.SEQ = 1;
        R000_WRITE_COMB_HIS();
        if (pgmRtn) return;
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        CIRBAS.STSW = "0" + CIRBAS.STSW.substring(1);
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        CIRBAS.STSW = CIRBAS.STSW.substring(0, 23 - 1) + "1" + CIRBAS.STSW.substring(23 + 1 - 1);
        T000_REWRITE_CITBAS();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_WRT_HIS_PROC_BAS();
        if (pgmRtn) return;
    }
    public void B020_01_CHECK_AGTLVL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGT);
        CIRAGT.KEY.ENTY_NO = CICCMCI.DATA.PRI_CI_NO;
        CIRAGT.KEY.ENTY_TYP = '0';
        T000_STARTBR_CITAGT_BY_ENTY();
        if (pgmRtn) return;
        T000_READNEXT_CITAGT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if (CIRAGT.AGT_LVL == 'C') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_LVL_CI);
            }
            T000_READNEXT_CITAGT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITAGT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRAGT);
        CIRAGT.CI_NO = CICCMCI.DATA.PRI_CI_NO;
        T000_STARTBR_CITAGT_BY_CI();
        if (pgmRtn) return;
        T000_READNEXT_CITAGT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if (CIRAGT.AGT_LVL == 'C') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_LVL_CI);
            }
            T000_READNEXT_CITAGT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITAGT();
        if (pgmRtn) return;
    }
    public void B020_03_CHECK_CARD_NUM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICCMCI.DATA.MAIN_CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRLS2);
        CIRLS2.KEY.LST_CD = "JGL";
        CIRLS2.KEY.ID_TYPE = CIRBAS.ID_TYPE;
        CIRLS2.KEY.ID_NO = CIRBAS.ID_NO;
        CIRLS2.KEY.CI_CNM = CIRBAS.CI_NM;
        T000_READ_CITLS2_FOR_JGL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, CICPCDL);
        BPCPRMR.FUNC = ' ';
        BPRPRMT.KEY.TYP = "CIACL";
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPRPRMT.KEY.CD = "02";
        } else {
            BPRPRMT.KEY.CD = "01";
        }
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        BPCPRMR.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICPCDL);
        WS_LIMIT_NUM = CICPCDL.NUM;
        WS_CITIZEN_FLG = CICPCDL.CITIZEN_FLG;
        CEP.TRC(SCCGWA, CICPCDL.NUM);
        CEP.TRC(SCCGWA, CICPCDL.CITIZEN_FLG);
        CEP.TRC(SCCGWA, WS_LIMIT_NUM);
        CEP.TRC(SCCGWA, WS_CITIZEN_FLG);
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CICCMCI.DATA.MAIN_CI_NO;
        CEP.TRC(SCCGWA, CIRACR.CI_NO);
        T000_STARTBR_CITACR_DC();
        if (pgmRtn) return;
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, CIRACR.PROD_CD);
            if ((WS_CITIZEN_FLG == 'Y' 
                || !CIRACR.PROD_CD.equalsIgnoreCase(K_CITIZEN_CARD)) 
                && !CIRACR.PROD_CD.equalsIgnoreCase(K_ALIEN_CARD)) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = CIRACR.OPN_BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                WS_I = 0;
                CEP.TRC(SCCGWA, BPCPQORG.VIL_TYP);
                while (WS_I != 10) {
                    WS_I = WS_I + 1;
                    CEP.TRC(SCCGWA, WS_CARD_LIMIT[WS_I-1].WS_VIL_TYPE);
                    if (WS_CARD_LIMIT[WS_I-1].WS_VIL_TYPE.trim().length() == 0) {
                        WS_CARD_LIMIT[WS_I-1].WS_VIL_TYPE = BPCPQORG.VIL_TYP;
                        WS_CARD_LIMIT[WS_I-1].WS_CARD_NUM = WS_CARD_LIMIT[WS_I-1].WS_CARD_NUM + 1;
                        CEP.TRC(SCCGWA, WS_CARD_LIMIT[WS_I-1].WS_CARD_NUM);
                        if (WS_CARD_LIMIT[WS_I-1].WS_CARD_NUM > WS_LIMIT_NUM) {
                            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CO_ERR_CARD_NUM);
                        }
                        WS_I = 10;
                    } else {
                        if (WS_CARD_LIMIT[WS_I-1].WS_VIL_TYPE.equalsIgnoreCase(BPCPQORG.VIL_TYP)) {
                            WS_CARD_LIMIT[WS_I-1].WS_CARD_NUM = WS_CARD_LIMIT[WS_I-1].WS_CARD_NUM + 1;
                            CEP.TRC(SCCGWA, WS_CARD_LIMIT[WS_I-1].WS_CARD_NUM);
                            if (WS_CARD_LIMIT[WS_I-1].WS_CARD_NUM > WS_LIMIT_NUM) {
                                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CO_ERR_CARD_NUM);
                            }
                            WS_I = 10;
                        }
                    }
                }
            }
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
    }
    public void B030_COMB_CITACR_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        WS_SEQ = 0;
        CIRACR.CI_NO = CICCMCI.DATA.PRI_CI_NO;
        T000_STARTBR_CITACR_BY_CI();
        if (pgmRtn) return;
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, CIRHIS);
            WS_SEQ = WS_SEQ + 1;
            CEP.TRC(SCCGWA, WS_SEQ);
            CIRHIS.KEY.FMT_ID = "CITACR";
            CIRHIS.KEY.SEQ = WS_SEQ;
            CIRHIS.BLOB_VAL = IBS.CLS2CPY(SCCGWA, CIRACR);
            R000_WRITE_COMB_HIS();
            if (pgmRtn) return;
            CIRACR.CI_NO = CICCMCI.DATA.MAIN_CI_NO;
            CIRACR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRACR.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRACR.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITACR();
            if (pgmRtn) return;
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
        B020_03_CHECK_CARD_NUM();
        if (pgmRtn) return;
    }
    public void B040_COMB_CITACAC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.CI_NO = CICCMCI.DATA.PRI_CI_NO;
        T000_STARTBR_CITACAC_BY_CI_UPD();
        if (pgmRtn) return;
        T000_READNEXT_CITACAC();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CIRACAC.CI_NO = CICCMCI.DATA.MAIN_CI_NO;
            CIRACAC.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRACAC.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRACAC.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITACAC();
            if (pgmRtn) return;
            T000_READNEXT_CITACAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACAC();
        if (pgmRtn) return;
    }
    public void B050_COMB_CITAGT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGT);
        WS_SEQ = 0;
        CIRAGT.KEY.ENTY_NO = CICCMCI.DATA.PRI_CI_NO;
        CIRAGT.KEY.ENTY_TYP = '0';
        T000_STARTBR_CITAGT_BY_ENTY_UPD();
        if (pgmRtn) return;
        T000_READNEXT_CITAGT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, CIRHIS);
            WS_SEQ = WS_SEQ + 1;
            CEP.TRC(SCCGWA, WS_SEQ);
            CIRHIS.KEY.FMT_ID = "CITAGT";
            CIRHIS.KEY.SEQ = WS_SEQ;
            CIRHIS.BLOB_VAL = IBS.CLS2CPY(SCCGWA, CIRAGT);
            R000_WRITE_COMB_HIS();
            if (pgmRtn) return;
            CIRAGT.KEY.ENTY_NO = CICCMCI.DATA.MAIN_CI_NO;
            CIRAGT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRAGT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRAGT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITAGT();
            if (pgmRtn) return;
            T000_READNEXT_CITAGT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITAGT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRAGT);
        CIRAGT.CI_NO = CICCMCI.DATA.PRI_CI_NO;
        T000_STARTBR_CITAGT_BY_CI_UPD();
        if (pgmRtn) return;
        T000_READNEXT_CITAGT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, CIRHIS);
            WS_SEQ = WS_SEQ + 1;
            CEP.TRC(SCCGWA, WS_SEQ);
            CIRHIS.KEY.FMT_ID = "CITAGT";
            CIRHIS.KEY.SEQ = WS_SEQ;
            CIRHIS.BLOB_VAL = IBS.CLS2CPY(SCCGWA, CIRAGT);
            R000_WRITE_COMB_HIS();
            if (pgmRtn) return;
            CIRAGT.CI_NO = CICCMCI.DATA.MAIN_CI_NO;
            CIRAGT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRAGT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRAGT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITAGT();
            if (pgmRtn) return;
            T000_READNEXT_CITAGT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITAGT();
        if (pgmRtn) return;
    }
    public void B070_COMB_DC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINO);
        DCCUCINO.FUNC = '1';
        DCCUCINO.CI_NO = CICCMCI.DATA.PRI_CI_NO;
        DCCUCINO.CI_TYP = 'P';
        DCCUCINO.NEW_CI_NO = CICCMCI.DATA.MAIN_CI_NO;
        S000_CALL_DCZUCINO();
        if (pgmRtn) return;
    }
    public void B080_COMB_EQ_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCSACT);
        EQCSACT.FUNC = 'C';
        EQCSACT.DATA.EQ_BKID = "01";
        EQCSACT.DATA.CI_NO = CICCMCI.DATA.MAIN_CI_NO;
        EQCSACT.DATA.OCI_NO = CICCMCI.DATA.PRI_CI_NO;
        S000_CALL_EQZSACT();
        if (pgmRtn) return;
    }
    public void B090_COMB_FX_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFXCUS);
        BPCFXCUS.FUNC = '1';
        BPCFXCUS.OLD_CI_NO = CICCMCI.DATA.PRI_CI_NO;
        BPCFXCUS.NEW_CI_NO = CICCMCI.DATA.MAIN_CI_NO;
        S000_CALL_BPZFXCUS();
        if (pgmRtn) return;
    }
    public void R000_WRITE_COMB_HIS() throws IOException,SQLException,Exception {
        CIRHIS.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIRHIS.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CIRHIS.KEY.CI_STS = 'N';
        CIRHIS.KEY.PRI_CI = CICCMCI.DATA.PRI_CI_NO;
        CIRHIS.CI_NO = CICCMCI.DATA.MAIN_CI_NO;
        CIRHIS.COMB_TYP = CICCMCI.DATA.COMB_TYP;
        CIRHIS.COMB_RES = CICCMCI.DATA.COMB_RES;
        CIRHIS.ELSE_RES = "" + CICCMCI.DATA.ELSE_RES;
        JIBS_tmp_int = CIRHIS.ELSE_RES.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) CIRHIS.ELSE_RES = "0" + CIRHIS.ELSE_RES;
        CIRHIS.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRHIS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRHIS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRHIS.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRHIS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITHIS();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRHIS, CIRHISN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        R000_WRT_HIS_PROC_HIS();
        if (pgmRtn) return;
    }
    public void R000_WRT_HIS_PROC_BAS() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRBAS";
        BPCPNHIS.INFO.FMT_ID_LEN = 568;
        BPCPNHIS.INFO.CI_NO = CICCMCI.DATA.PRI_CI_NO;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRBASO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRBASN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_WRT_HIS_PROC_HIS() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRHIS";
        BPCPNHIS.INFO.FMT_ID_LEN = 12;
        BPCPNHIS.INFO.CI_NO = CICCMCI.DATA.MAIN_CI_NO;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
    }
    public void S000_CALL_DCZUCINO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-UPDATE-CI-NO", DCCUCINO);
        if (DCCUCINO.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUCINO.RC);
        }
    }
    public void S000_CALL_EQZSACT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "EQ-S-MAIN-SACT", EQCSACT);
        if (EQCSACT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, EQCSACT.RC);
        }
    }
    public void S000_CALL_BPZFXCUS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX-UPD-CUS", BPCFXCUS);
        if (BPCFXCUS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFXCUS.RC);
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCPRMR.RC);
        }
    }
    public void S000_CALL_DDZSZFJZ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-M-DDZSZFJZ", DDCSZFJZ, true);
    }
    public void S001_CALL_DCZIQHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-DCZIQHLD", DCCIQHLD, true);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORG.RC);
        }
    }
    public void S000_CALL_DDZRFBID() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SRC-DDZRFBID", DDCRFBID);
        if (DDCRFBID.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCRFBID.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITBAS_UPD() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.upd = true;
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_REWRITE_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.REWRITE(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITLS2_FOR_JGL() throws IOException,SQLException,Exception {
        CITLS2_RD = new DBParm();
        CITLS2_RD.TableName = "CITLS2";
        CITLS2_RD.eqWhere = "LST_CD , ID_TYPE , ID_NO , CI_CNM";
        IBS.READ(SCCGWA, CIRLS2, CITLS2_RD);
    }
    public void T000_STARTBR_CITACAC_BY_CI() throws IOException,SQLException,Exception {
        CITACAC_BR.rp = new DBParm();
        CITACAC_BR.rp.TableName = "CITACAC";
        CITACAC_BR.rp.eqWhere = "CI_NO";
        IBS.STARTBR(SCCGWA, CIRACAC, CITACAC_BR);
    }
    public void T000_STARTBR_CITACAC_BY_CI_UPD() throws IOException,SQLException,Exception {
        CITACAC_BR.rp = new DBParm();
        CITACAC_BR.rp.TableName = "CITACAC";
        CITACAC_BR.rp.eqWhere = "CI_NO";
        CITACAC_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, CIRACAC, CITACAC_BR);
    }
    public void T000_READNEXT_CITACAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACAC, this, CITACAC_BR);
    }
    public void T000_ENDBR_CITACAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACAC_BR);
    }
    public void T000_REWRITE_CITACAC() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        IBS.REWRITE(SCCGWA, CIRACAC, CITACAC_RD);
    }
    public void T000_STARTBR_CITACR_DC() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        CITACR_BR.rp.where = "ENTY_TYP = '2' "
            + "AND STS = '0'";
        IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_STARTBR_CITACR_BY_CI() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        CITACR_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, CIRACR, CITACR_BR);
    }
    public void T000_READNEXT_CITACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_ENDBR_CITACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACR_BR);
    }
    public void T000_REWRITE_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.REWRITE(SCCGWA, CIRACR, CITACR_RD);
    }
    public void T000_STARTBR_CITAGT_BY_ENTY() throws IOException,SQLException,Exception {
        CITAGT_BR.rp = new DBParm();
        CITAGT_BR.rp.TableName = "CITAGT";
        CITAGT_BR.rp.eqWhere = "ENTY_TYP,ENTY_NO";
        IBS.STARTBR(SCCGWA, CIRAGT, CITAGT_BR);
    }
    public void T000_STARTBR_CITAGT_BY_ENTY_UPD() throws IOException,SQLException,Exception {
        CITAGT_BR.rp = new DBParm();
        CITAGT_BR.rp.TableName = "CITAGT";
        CITAGT_BR.rp.eqWhere = "ENTY_TYP,ENTY_NO";
        CITAGT_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, CIRAGT, CITAGT_BR);
    }
    public void T000_STARTBR_CITAGT_BY_CI() throws IOException,SQLException,Exception {
        CITAGT_BR.rp = new DBParm();
        CITAGT_BR.rp.TableName = "CITAGT";
        CITAGT_BR.rp.eqWhere = "CI_NO";
        IBS.STARTBR(SCCGWA, CIRAGT, CITAGT_BR);
    }
    public void T000_STARTBR_CITAGT_BY_CI_UPD() throws IOException,SQLException,Exception {
        CITAGT_BR.rp = new DBParm();
        CITAGT_BR.rp.TableName = "CITAGT";
        CITAGT_BR.rp.eqWhere = "CI_NO";
        CITAGT_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, CIRAGT, CITAGT_BR);
    }
    public void T000_READNEXT_CITAGT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRAGT, this, CITAGT_BR);
    }
    public void T000_ENDBR_CITAGT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITAGT_BR);
    }
    public void T000_REWRITE_CITAGT() throws IOException,SQLException,Exception {
        CITAGT_RD = new DBParm();
        CITAGT_RD.TableName = "CITAGT";
        IBS.REWRITE(SCCGWA, CIRAGT, CITAGT_RD);
    }
    public void T000_READ_CITLIMT() throws IOException,SQLException,Exception {
        CITLIMT_RD = new DBParm();
        CITLIMT_RD.TableName = "CITLIMT";
        CITLIMT_RD.eqWhere = "CI_NO , LMT_TP";
        CITLIMT_RD.where = "EXP_DT > :WS_AC_DATE";
        IBS.READ(SCCGWA, CIRLIMT, this, CITLIMT_RD);
    }
    public void T000_READ_CITLIMT_UPD() throws IOException,SQLException,Exception {
        CITLIMT_RD = new DBParm();
        CITLIMT_RD.TableName = "CITLIMT";
        CITLIMT_RD.eqWhere = "CI_NO , LMT_TP";
        CITLIMT_RD.where = "EXP_DT > :WS_AC_DATE";
        CITLIMT_RD.upd = true;
        IBS.READ(SCCGWA, CIRLIMT, this, CITLIMT_RD);
    }
    public void T000_REWRITE_CITLIMT() throws IOException,SQLException,Exception {
        CITLIMT_RD = new DBParm();
        CITLIMT_RD.TableName = "CITLIMT";
        IBS.REWRITE(SCCGWA, CIRLIMT, CITLIMT_RD);
    }
    public void T000_WRITE_CITLIMT() throws IOException,SQLException,Exception {
        CITLIMT_RD = new DBParm();
        CITLIMT_RD.TableName = "CITLIMT";
        IBS.WRITE(SCCGWA, CIRLIMT, CITLIMT_RD);
    }
    public void T000_WRITE_CITHIS() throws IOException,SQLException,Exception {
        CITHIS_RD = new DBParm();
        CITHIS_RD.TableName = "CITHIS";
        IBS.WRITE(SCCGWA, CIRHIS, CITHIS_RD);
    }
    public void T000_START_BROWSE_FBID() throws IOException,SQLException,Exception {
        DDCRFBID.FUNC = 'B';
        DDCRFBID.OPT = 'K';
        DDCRFBID.REC_PTR = DDRFBID;
        DDCRFBID.REC_LEN = 2201;
        S000_CALL_DDZRFBID();
        if (pgmRtn) return;
    }
    public void T000_READ_NEXT_FBID() throws IOException,SQLException,Exception {
        DDCRFBID.FUNC = 'B';
        DDCRFBID.OPT = 'R';
        DDCRFBID.REC_PTR = DDRFBID;
        DDCRFBID.REC_LEN = 2201;
        S000_CALL_DDZRFBID();
        if (pgmRtn) return;
    }
    public void T000_END_BROWSE_FBID() throws IOException,SQLException,Exception {
        DDCRFBID.FUNC = 'B';
        DDCRFBID.OPT = 'E';
        DDCRFBID.REC_PTR = DDRFBID;
        DDCRFBID.REC_LEN = 2201;
        S000_CALL_DDZRFBID();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
