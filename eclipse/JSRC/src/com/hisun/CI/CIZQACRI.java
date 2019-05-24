package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.AI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZQACRI {
    int JIBS_tmp_int;
    int ACR_AC_CNM_LEN;
    int ACR_AC_ENM_LEN;
    int BAS_CI_NM_LEN;
    int BAS_TAX_BANK_LEN;
    DBParm CITACR_RD;
    DBParm CITBAS_RD;
    brParm CITACR_BR = new brParm();
    DBParm CITACRL_RD;
    String JIBS_tmp_str[] = new String[10];
    DBParm CITNAM_RD;
    DBParm CITFLRL_RD;
    boolean pgmRtn = false;
    short K_MAX_BASC_COUNT = 2;
    short K_MAX_ACRC_COUNT = 2;
    int K_MAX_ROW = 20;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    String WS_AC_NM = " ";
    short WS_I = 0;
    char WS_ACR_FLG = ' ';
    char WS_AC_ROUTING_FLG = ' ';
    short WS_BASC_COUNT = 0;
    short WS_BASC_I = 0;
    char WS_BASC_FLG = ' ';
    short WS_ACRC_COUNT = 0;
    short WS_ACRC_I = 0;
    char WS_ACRC_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRACR CIRACR = new CIRACR();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACRL CIRACRL = new CIRACRL();
    CIRNAM CIRNAM = new CIRNAM();
    CIRFLRL CIRFLRL = new CIRFLRL();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICOACRB CICOACRB = new CICOACRB();
    CICQCHDC CICQCHDC = new CICQCHDC();
    AICPQMIB AICPQMIB = new AICPQMIB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICQACRI CICQACRI;
    CICBASC CICBASC;
    CICACRC CICACRC;
    public void MP(SCCGWA SCCGWA, CICQACRI CICQACRI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICQACRI = CICQACRI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZQACRI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CICBASC = (CICBASC) SCCGWA.COMM_AREA.CITBAS_PTR;
        CICACRC = (CICACRC) SCCGWA.COMM_AREA.CITACR_PTR;
        IBS.init(SCCGWA, CICOACRB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICQACRI.FUNC == 'A') {
            B020_INQUIRE_ACR_INF_BY_AC();
            if (pgmRtn) return;
        } else if (CICQACRI.FUNC == 'C') {
            B030_BROWSE_AGR_NO_BY_ACNM();
            if (pgmRtn) return;
        } else if (CICQACRI.FUNC == 'N') {
            B040_BROWSE_ACR_INF_BY_ACNM();
            if (pgmRtn) return;
        } else if (CICQACRI.FUNC == 'I') {
            B050_BROWSE_AGR_NO_BY_CINO();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "FUNC CODE INPUT ERROR");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR, CICQACRI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_INQUIRE_ACR_INF_BY_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACRI.DATA.AGR_NO);
        if (CICQACRI.DATA.AGR_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AC-NO MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICQACRI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICQACRI.DATA.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        CICQACRI.O_DATA.O_AGR_NO_OLD = CIRACR.KEY.AGR_NO;
        CICQACRI.O_DATA.O_STS_OLD = CIRACR.STS;
        CICQACRI.O_DATA.O_FRM_APP_OLD = CIRACR.FRM_APP;
        if (WS_ACR_FLG == 'Y') {
            if (CIRACR.STSW == null) CIRACR.STSW = "";
            JIBS_tmp_int = CIRACR.STSW.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) CIRACR.STSW += " ";
            if (CIRACR.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                && (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT"))) {
                IBS.init(SCCGWA, CICQCHDC);
                CICQCHDC.DATA.O_AGR_NO = CIRACR.KEY.AGR_NO;
                CICQCHDC.DATA.O_ENTY_TYP = CIRACR.ENTY_TYP;
                CICQCHDC.FUNC = 'W';
                S000_CALL_CIZQCHDC();
                if (pgmRtn) return;
                IBS.init(SCCGWA, CIRACR);
                CIRACR.KEY.AGR_NO = CICQCHDC.DATA.N_AGR_NO;
                T000_READ_CITACR();
                if (pgmRtn) return;
            }
            R000_TRANS_DATA_TO_OUTPUT_AC();
            if (pgmRtn) return;
        } else {
            R000_CHECK_INSIDE_AC();
            if (pgmRtn) return;
        }
    }
    public void B030_BROWSE_AGR_NO_BY_ACNM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACRI.DATA.AC_CNM);
        if (CICQACRI.DATA.AC_CNM.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AC-CNM MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICQACRI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACR);
        CIRACR.AC_CNM = CICQACRI.DATA.AC_CNM;
        ACR_AC_CNM_LEN = CIRACR.AC_CNM.length();
        CEP.TRC(SCCGWA, ACR_AC_CNM_LEN);
        if (ACR_AC_CNM_LEN < 252) {
            ACR_AC_CNM_LEN = ACR_AC_CNM_LEN + 1;
            if (CIRACR.AC_CNM == null) CIRACR.AC_CNM = "";
            JIBS_tmp_int = CIRACR.AC_CNM.length();
            for (int i=0;i<252-JIBS_tmp_int;i++) CIRACR.AC_CNM += " ";
            CIRACR.AC_CNM = CIRACR.AC_CNM.substring(0, ACR_AC_CNM_LEN - 1) + "%" + CIRACR.AC_CNM.substring(ACR_AC_CNM_LEN + 1 - 1);
        }
        CEP.TRC(SCCGWA, CIRACR.AC_CNM);
        CEP.TRC(SCCGWA, ACR_AC_CNM_LEN);
        T000_STARTBR_CITACR_BY_ACNM();
        if (pgmRtn) return;
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            CEP.TRC(SCCGWA, "ACR INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICQACRI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        for (WS_I = 1; SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_I <= 3000; WS_I += 1) {
            CICQACRI.AGR_NO_AREA[WS_I-1].AC_NO = CIRACR.KEY.AGR_NO;
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
    }
    public void B040_BROWSE_ACR_INF_BY_ACNM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACRI.DATA.AC_CNM);
        if (CICQACRI.DATA.AC_CNM.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AC-CNM MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICQACRI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACR);
        CIRACR.AC_CNM = CICQACRI.DATA.AC_CNM;
        ACR_AC_CNM_LEN = CIRACR.AC_CNM.length();
        T000_STARTBR_CITACR_BY_ACNM();
        if (pgmRtn) return;
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            R000_MPAG_OUT_TATLE();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "ACR INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICQACRI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            R000_MPAG_OUT_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_AGR_NO_BY_CINO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACRI.DATA.CI_NO);
        if (CICQACRI.DATA.CI_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "CI-NO MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICQACRI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICQACRI.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "BAS INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICQACRI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CICQACRI.DATA.CI_NO;
        T000_STARTBR_CITACR_BY_CINO();
        if (pgmRtn) return;
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            CEP.TRC(SCCGWA, "ACR INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICQACRI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        for (WS_I = 1; SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_I <= 3000; WS_I += 1) {
            CICQACRI.AGR_NO_AREA[WS_I-1].AC_NO = CIRACR.KEY.AGR_NO;
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_TO_OUTPUT_AC() throws IOException,SQLException,Exception {
        CICQACRI.O_DATA.O_AGR_NO = CIRACR.KEY.AGR_NO;
        CICQACRI.O_DATA.O_ENTY_TYP = CIRACR.ENTY_TYP;
        CICQACRI.O_DATA.O_CI_NO = CIRACR.CI_NO;
        CICQACRI.O_DATA.O_STS = CIRACR.STS;
        CICQACRI.O_DATA.O_STSW = CIRACR.STSW;
        CICQACRI.O_DATA.O_PROD_CD = CIRACR.PROD_CD;
        CICQACRI.O_DATA.O_CNTRCT_TYP = CIRACR.CNTRCT_TYP;
        CICQACRI.O_DATA.O_FRM_APP = CIRACR.FRM_APP;
        CICQACRI.O_DATA.O_CCY = CIRACR.CCY;
        CICQACRI.O_DATA.O_SHOW_FLG = CIRACR.SHOW_FLG;
        CICQACRI.O_DATA.O_SMS_FLG = CIRACR.SMS_FLG;
        CICQACRI.O_DATA.O_OPN_BR = CIRACR.OPN_BR;
        CICQACRI.O_DATA.O_OPEN_DT = CIRACR.OPEN_DT;
        CICQACRI.O_DATA.O_CLOSE_DT = CIRACR.CLOSE_DT;
        CICQACRI.O_DATA.O_OWNER_BK = CIRACR.OWNER_BK;
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIRACR.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        CICQACRI.O_DATA.O_CI_TYP = CIRBAS.CI_TYP;
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.KEY.CI_NO = CIRACR.CI_NO;
        CIRNAM.KEY.NM_TYPE = "03";
        T000_READ_CITNAM();
        if (pgmRtn) return;
        if (CIRBAS.CI_TYP == '1') {
            CICQACRI.O_DATA.O_AC_CNM = CIRBAS.CI_NM;
            CICQACRI.O_DATA.O_AC_ENM = CIRNAM.CI_NM;
        } else if (CIRBAS.CI_TYP == '2' 
                && CIRACR.FRM_APP.equalsIgnoreCase("DC")) {
            IBS.init(SCCGWA, CIRACRL);
            CIRACRL.KEY.AC_NO = CIRACR.KEY.AGR_NO;
            CIRACRL.KEY.AC_REL = "04";
            T000_READ_CITACRL_BY_ACNO();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.TRC(SCCGWA, CIRACRL.KEY.REL_AC_NO);
                IBS.init(SCCGWA, CIRACR);
                CIRACR.KEY.AGR_NO = CIRACRL.KEY.REL_AC_NO;
                T000_READ_CITACR();
                if (pgmRtn) return;
                CICQACRI.O_DATA.O_AC_CNM = CIRACR.AC_CNM;
                CICQACRI.O_DATA.O_AC_ENM = CIRACR.AC_ENM;
            }
        } else {
            CICQACRI.O_DATA.O_AC_CNM = CIRACR.AC_CNM;
            CICQACRI.O_DATA.O_AC_ENM = CIRACR.AC_ENM;
        }
    }
    public void R000_MPAG_OUT_TATLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_MPAG_OUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOACRB);
        CICOACRB.DATA.AGR_NO = CIRACR.KEY.AGR_NO;
        CICOACRB.DATA.ENTY_TYP = CIRACR.ENTY_TYP;
        CICOACRB.DATA.CI_NO = CIRACR.CI_NO;
        CICOACRB.DATA.STS = CIRACR.STS;
        CICOACRB.DATA.STSW = CIRACR.STSW;
        CICOACRB.DATA.PROD_CD = CIRACR.PROD_CD;
        CICOACRB.DATA.CNTRCT_TYP = CIRACR.CNTRCT_TYP;
        CICOACRB.DATA.FRM_APP = CIRACR.FRM_APP;
        CICOACRB.DATA.CCY = CIRACR.CCY;
        CICOACRB.DATA.SHOW_FLG = CIRACR.SHOW_FLG;
        CICOACRB.DATA.SMS_FLG = CIRACR.SMS_FLG;
        CICOACRB.DATA.AC_CNM = CIRACR.AC_CNM;
        CICOACRB.DATA.AC_ENM = CIRACR.AC_ENM;
        CICOACRB.DATA.OPN_BR = CIRACR.OPN_BR;
        CICOACRB.DATA.OPEN_DT = CIRACR.OPEN_DT;
        CICOACRB.DATA.CLOSE_DT = CIRACR.CLOSE_DT;
        CICOACRB.DATA.OWNER_BK = CIRACR.OWNER_BK;
        CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
        CEP.TRC(SCCGWA, CIRACR.ENTY_TYP);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOACRB);
        SCCMPAG.DATA_LEN = 607;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_CHECK_INSIDE_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = CICQACRI.DATA.AGR_NO;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
        if (WS_AC_ROUTING_FLG == 'Y') {
            CICQACRI.O_DATA.O_AGR_NO = CICQACRI.DATA.AGR_NO;
            CICQACRI.O_DATA.O_FRM_APP = "AI";
            CICQACRI.O_DATA.O_OPN_BR = AICPQMIB.INPUT_DATA.BR;
        } else {
            CEP.TRC(SCCGWA, "AC NOT EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AC_NOT_EXIST, CICQACRI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_TRANS_ACRC_TO_ACR() throws IOException,SQLException,Exception {
        CIRACR.KEY.AGR_NO = CICACRC.DATA[WS_ACRC_I-1].AGR_NO;
        CIRACR.ENTY_TYP = CICACRC.DATA[WS_ACRC_I-1].ENTY_TYP;
        CIRACR.CI_NO = CICACRC.DATA[WS_ACRC_I-1].CI_NO;
        CIRACR.STS = CICACRC.DATA[WS_ACRC_I-1].STS;
        CIRACR.STSW = CICACRC.DATA[WS_ACRC_I-1].STSW;
        CIRACR.PROD_CD = CICACRC.DATA[WS_ACRC_I-1].PROD_CD;
        CIRACR.CNTRCT_TYP = CICACRC.DATA[WS_ACRC_I-1].CNTRCT_TYP;
        CIRACR.FRM_APP = CICACRC.DATA[WS_ACRC_I-1].FRM_APP;
        CIRACR.CCY = CICACRC.DATA[WS_ACRC_I-1].CCY;
        CIRACR.SHOW_FLG = CICACRC.DATA[WS_ACRC_I-1].SHOW_FLG;
        CIRACR.SMS_FLG = CICACRC.DATA[WS_ACRC_I-1].SMS_FLG;
        CIRACR.AC_CNM = CICACRC.DATA[WS_ACRC_I-1].AC_CNM;
        CIRACR.AC_ENM = CICACRC.DATA[WS_ACRC_I-1].AC_ENM;
        ACR_AC_CNM_LEN = CIRACR.AC_CNM.length();
        ACR_AC_ENM_LEN = CIRACR.AC_ENM.length();
        CIRACR.AC_SEQ = CICACRC.DATA[WS_ACRC_I-1].AC_SEQ;
        CIRACR.OPN_BR = CICACRC.DATA[WS_ACRC_I-1].OPN_BR;
        CIRACR.OPEN_DT = CICACRC.DATA[WS_ACRC_I-1].OPEN_DT;
        CIRACR.CLOSE_DT = CICACRC.DATA[WS_ACRC_I-1].CLOSE_DT;
        CIRACR.OWNER_BK = CICACRC.DATA[WS_ACRC_I-1].OWNER_BK;
        CIRACR.CRT_TLR = CICACRC.DATA[WS_ACRC_I-1].CRT_TLR;
        CIRACR.CRT_BR = CICACRC.DATA[WS_ACRC_I-1].CRT_BR;
        CIRACR.CRT_DT = CICACRC.DATA[WS_ACRC_I-1].CRT_DT;
        CIRACR.UPD_TLR = CICACRC.DATA[WS_ACRC_I-1].UPD_TLR;
        CIRACR.UPD_BR = CICACRC.DATA[WS_ACRC_I-1].UPD_BR;
        CIRACR.UPD_DT = CICACRC.DATA[WS_ACRC_I-1].UPD_DT;
        CIRACR.TS = CICACRC.DATA[WS_ACRC_I-1].TS;
        CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
        CEP.TRC(SCCGWA, WS_ACRC_I);
    }
    public void R000_WRITE_ACRC() throws IOException,SQLException,Exception {
        CICACRC.COUNT = (short) (CICACRC.COUNT + 1);
        CICACRC.DATA[CICACRC.COUNT-1].AGR_NO = CIRACR.KEY.AGR_NO;
        CICACRC.DATA[CICACRC.COUNT-1].ENTY_TYP = CIRACR.ENTY_TYP;
        CICACRC.DATA[CICACRC.COUNT-1].CI_NO = CIRACR.CI_NO;
        CICACRC.DATA[CICACRC.COUNT-1].STS = CIRACR.STS;
        CICACRC.DATA[CICACRC.COUNT-1].STSW = CIRACR.STSW;
        CICACRC.DATA[CICACRC.COUNT-1].PROD_CD = CIRACR.PROD_CD;
        CICACRC.DATA[CICACRC.COUNT-1].CNTRCT_TYP = CIRACR.CNTRCT_TYP;
        CICACRC.DATA[CICACRC.COUNT-1].FRM_APP = CIRACR.FRM_APP;
        CICACRC.DATA[CICACRC.COUNT-1].CCY = CIRACR.CCY;
        CICACRC.DATA[CICACRC.COUNT-1].SHOW_FLG = CIRACR.SHOW_FLG;
        CICACRC.DATA[CICACRC.COUNT-1].SMS_FLG = CIRACR.SMS_FLG;
        CICACRC.DATA[CICACRC.COUNT-1].AC_CNM = CIRACR.AC_CNM;
        CICACRC.DATA[CICACRC.COUNT-1].AC_ENM = CIRACR.AC_ENM;
        CICACRC.DATA[CICACRC.COUNT-1].AC_SEQ = CIRACR.AC_SEQ;
        CICACRC.DATA[CICACRC.COUNT-1].OPN_BR = CIRACR.OPN_BR;
        CICACRC.DATA[CICACRC.COUNT-1].OPEN_DT = CIRACR.OPEN_DT;
        CICACRC.DATA[CICACRC.COUNT-1].CLOSE_DT = CIRACR.CLOSE_DT;
        CICACRC.DATA[CICACRC.COUNT-1].OWNER_BK = CIRACR.OWNER_BK;
        CICACRC.DATA[CICACRC.COUNT-1].CRT_TLR = CIRACR.CRT_TLR;
        CICACRC.DATA[CICACRC.COUNT-1].CRT_BR = CIRACR.CRT_BR;
        CICACRC.DATA[CICACRC.COUNT-1].CRT_DT = CIRACR.CRT_DT;
        CICACRC.DATA[CICACRC.COUNT-1].UPD_TLR = CIRACR.UPD_TLR;
        CICACRC.DATA[CICACRC.COUNT-1].UPD_BR = CIRACR.UPD_BR;
        CICACRC.DATA[CICACRC.COUNT-1].UPD_DT = CIRACR.UPD_DT;
        CICACRC.DATA[CICACRC.COUNT-1].TS = CIRACR.TS;
        CEP.TRC(SCCGWA, CICACRC.COUNT);
        CEP.TRC(SCCGWA, CICACRC.DATA[CICACRC.COUNT-1].AGR_NO);
    }
    public void R000_TRANS_BASC_TO_BAS() throws IOException,SQLException,Exception {
        CIRBAS.KEY.CI_NO = CICBASC.DATA[WS_BASC_I-1].CI_NO;
        CIRBAS.CI_TYP = CICBASC.DATA[WS_BASC_I-1].CI_TYP;
        CIRBAS.CI_ATTR = CICBASC.DATA[WS_BASC_I-1].CI_ATTR;
        CIRBAS.SVR_LVL = CICBASC.DATA[WS_BASC_I-1].SVR_LVL;
        CIRBAS.SVR_LVL1 = CICBASC.DATA[WS_BASC_I-1].SVR_LVL1;
        CIRBAS.SVR_LVL2 = CICBASC.DATA[WS_BASC_I-1].SVR_LVL2;
        CIRBAS.SVR_LVL3 = CICBASC.DATA[WS_BASC_I-1].SVR_LVL3;
        CIRBAS.STSW = CICBASC.DATA[WS_BASC_I-1].STSW;
        CIRBAS.VER_STSW = CICBASC.DATA[WS_BASC_I-1].VER_STSW;
        CIRBAS.IDE_STSW = CICBASC.DATA[WS_BASC_I-1].IDE_STSW;
        CIRBAS.OPN_BR = CICBASC.DATA[WS_BASC_I-1].OPN_BR;
        CIRBAS.OPEN_DT = CICBASC.DATA[WS_BASC_I-1].OPEN_DT;
        CIRBAS.CLOSE_DT = CICBASC.DATA[WS_BASC_I-1].CLOSE_DT;
        CIRBAS.ORGIN_TP = CICBASC.DATA[WS_BASC_I-1].ORGIN_TP;
        CIRBAS.ORIGIN = CICBASC.DATA[WS_BASC_I-1].ORIGIN;
        CIRBAS.ORIGIN2 = CICBASC.DATA[WS_BASC_I-1].ORIGIN2;
        CIRBAS.CI_NM = CICBASC.DATA[WS_BASC_I-1].CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        CIRBAS.ID_TYPE = CICBASC.DATA[WS_BASC_I-1].ID_TYPE;
        CIRBAS.ID_NO = CICBASC.DATA[WS_BASC_I-1].ID_NO;
        CIRBAS.ID_RGN = CICBASC.DATA[WS_BASC_I-1].ID_RGN;
        CIRBAS.TAX_BANK = CICBASC.DATA[WS_BASC_I-1].TAX_BANK;
        BAS_TAX_BANK_LEN = CIRBAS.TAX_BANK.length();
        CIRBAS.TAX_AC_NO = CICBASC.DATA[WS_BASC_I-1].TAX_AC_NO;
        CIRBAS.TAX_TYPE = CICBASC.DATA[WS_BASC_I-1].TAX_TYPE;
        CIRBAS.TAX_DIST_NO = CICBASC.DATA[WS_BASC_I-1].TAX_DIST_NO;
        CIRBAS.FTA_FLG = CICBASC.DATA[WS_BASC_I-1].FTA_FLG;
        CIRBAS.OWNER_BK = CICBASC.DATA[WS_BASC_I-1].OWNER_BK;
        CIRBAS.OWNER_BR = CICBASC.DATA[WS_BASC_I-1].OWNER_BR;
        CIRBAS.OIC_NO = CICBASC.DATA[WS_BASC_I-1].OIC_NO;
        CIRBAS.RESP_CD = CICBASC.DATA[WS_BASC_I-1].RESP_CD;
        CIRBAS.SUB_DP = CICBASC.DATA[WS_BASC_I-1].SUB_DP;
        CIRBAS.OPEN_CHNL = CICBASC.DATA[WS_BASC_I-1].OPEN_CHNL;
        CIRBAS.NRA_TAX_TYP = CICBASC.DATA[WS_BASC_I-1].NRA_TAX_TYP;
        CIRBAS.CRT_TLR = CICBASC.DATA[WS_BASC_I-1].CRT_TLR;
        CIRBAS.CRT_DT = CICBASC.DATA[WS_BASC_I-1].CRT_DT;
        CIRBAS.CRT_BR = CICBASC.DATA[WS_BASC_I-1].CRT_BR;
        CIRBAS.UPD_TLR = CICBASC.DATA[WS_BASC_I-1].UPD_TLR;
        CIRBAS.UPD_BR = CICBASC.DATA[WS_BASC_I-1].UPD_BR;
        CIRBAS.UPD_DT = CICBASC.DATA[WS_BASC_I-1].UPD_DT;
        CIRBAS.TS = CICBASC.DATA[WS_BASC_I-1].TS;
        CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
        CEP.TRC(SCCGWA, WS_BASC_I);
    }
    public void R000_WRITE_BASC() throws IOException,SQLException,Exception {
        CICBASC.COUNT = (short) (CICBASC.COUNT + 1);
        CICBASC.DATA[CICBASC.COUNT-1].CI_NO = CIRBAS.KEY.CI_NO;
        CICBASC.DATA[CICBASC.COUNT-1].CI_TYP = CIRBAS.CI_TYP;
        CICBASC.DATA[CICBASC.COUNT-1].CI_ATTR = CIRBAS.CI_ATTR;
        CICBASC.DATA[CICBASC.COUNT-1].SVR_LVL = CIRBAS.SVR_LVL;
        CICBASC.DATA[CICBASC.COUNT-1].SVR_LVL1 = CIRBAS.SVR_LVL1;
        CICBASC.DATA[CICBASC.COUNT-1].SVR_LVL2 = CIRBAS.SVR_LVL2;
        CICBASC.DATA[CICBASC.COUNT-1].SVR_LVL3 = CIRBAS.SVR_LVL3;
        CICBASC.DATA[CICBASC.COUNT-1].STSW = CIRBAS.STSW;
        CICBASC.DATA[CICBASC.COUNT-1].VER_STSW = CIRBAS.VER_STSW;
        CICBASC.DATA[CICBASC.COUNT-1].IDE_STSW = CIRBAS.IDE_STSW;
        CICBASC.DATA[CICBASC.COUNT-1].OPN_BR = CIRBAS.OPN_BR;
        CICBASC.DATA[CICBASC.COUNT-1].OPEN_DT = CIRBAS.OPEN_DT;
        CICBASC.DATA[CICBASC.COUNT-1].CLOSE_DT = CIRBAS.CLOSE_DT;
        CICBASC.DATA[CICBASC.COUNT-1].ORGIN_TP = CIRBAS.ORGIN_TP;
        CICBASC.DATA[CICBASC.COUNT-1].ORIGIN = CIRBAS.ORIGIN;
        CICBASC.DATA[CICBASC.COUNT-1].ORIGIN2 = CIRBAS.ORIGIN2;
        if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
        JIBS_tmp_int = CIRBAS.CI_NM.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
        CICBASC.DATA[CICBASC.COUNT-1].CI_NM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN);
        CICBASC.DATA[CICBASC.COUNT-1].ID_TYPE = CIRBAS.ID_TYPE;
        CICBASC.DATA[CICBASC.COUNT-1].ID_NO = CIRBAS.ID_NO;
        CICBASC.DATA[CICBASC.COUNT-1].ID_RGN = CIRBAS.ID_RGN;
        if (CIRBAS.TAX_BANK == null) CIRBAS.TAX_BANK = "";
        JIBS_tmp_int = CIRBAS.TAX_BANK.length();
        for (int i=0;i<140-JIBS_tmp_int;i++) CIRBAS.TAX_BANK += " ";
        CICBASC.DATA[CICBASC.COUNT-1].TAX_BANK = CIRBAS.TAX_BANK.substring(0, BAS_TAX_BANK_LEN);
        CICBASC.DATA[CICBASC.COUNT-1].TAX_AC_NO = CIRBAS.TAX_AC_NO;
        CICBASC.DATA[CICBASC.COUNT-1].TAX_TYPE = CIRBAS.TAX_TYPE;
        CICBASC.DATA[CICBASC.COUNT-1].TAX_DIST_NO = CIRBAS.TAX_DIST_NO;
        CICBASC.DATA[CICBASC.COUNT-1].FTA_FLG = CIRBAS.FTA_FLG;
        CICBASC.DATA[CICBASC.COUNT-1].OWNER_BK = CIRBAS.OWNER_BK;
        CICBASC.DATA[CICBASC.COUNT-1].OWNER_BR = CIRBAS.OWNER_BR;
        CICBASC.DATA[CICBASC.COUNT-1].OIC_NO = CIRBAS.OIC_NO;
        CICBASC.DATA[CICBASC.COUNT-1].RESP_CD = CIRBAS.RESP_CD;
        CICBASC.DATA[CICBASC.COUNT-1].SUB_DP = CIRBAS.SUB_DP;
        CICBASC.DATA[CICBASC.COUNT-1].OPEN_CHNL = CIRBAS.OPEN_CHNL;
        CICBASC.DATA[CICBASC.COUNT-1].NRA_TAX_TYP = CIRBAS.NRA_TAX_TYP;
        CICBASC.DATA[CICBASC.COUNT-1].CRT_TLR = CIRBAS.CRT_TLR;
        CICBASC.DATA[CICBASC.COUNT-1].CRT_DT = CIRBAS.CRT_DT;
        CICBASC.DATA[CICBASC.COUNT-1].CRT_BR = CIRBAS.CRT_BR;
        CICBASC.DATA[CICBASC.COUNT-1].UPD_TLR = CIRBAS.UPD_TLR;
        CICBASC.DATA[CICBASC.COUNT-1].UPD_BR = CIRBAS.UPD_BR;
        CICBASC.DATA[CICBASC.COUNT-1].UPD_DT = CIRBAS.UPD_DT;
        CICBASC.DATA[CICBASC.COUNT-1].TS = CIRBAS.TS;
        CEP.TRC(SCCGWA, CICBASC.COUNT);
        CEP.TRC(SCCGWA, CICBASC.DATA[CICBASC.COUNT-1].CI_NO);
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        WS_ACRC_I = 0;
        WS_ACRC_FLG = ' ';
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        while (WS_ACRC_I < CICACRC.COUNT 
            && WS_ACRC_FLG != 'Y') {
            WS_ACRC_I = (short) (WS_ACRC_I + 1);
            CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
            if (CIRACR.KEY.AGR_NO.equalsIgnoreCase(CICACRC.DATA[WS_ACRC_I-1].AGR_NO)) {
                CEP.TRC(SCCGWA, "READ ACR CASHE");
                R000_TRANS_ACRC_TO_ACR();
                if (pgmRtn) return;
                WS_ACRC_FLG = 'Y';
                SCCGWA.COMM_AREA.DBIO_FLG = '0';
                WS_ACR_FLG = 'Y';
            }
        }
    } //FROM #ENDIF
        if (WS_ACRC_FLG != 'Y') {
            CEP.TRC(SCCGWA, "READ ACR TABLE");
            CITACR_RD = new DBParm();
            CITACR_RD.TableName = "CITACR";
            IBS.READ(SCCGWA, CIRACR, CITACR_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_ACR_FLG = 'Y';
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_ACR_FLG = 'N';
            } else {
                IBS.init(SCCGWA, SCCEXCP);
                SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
                SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
                SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
                SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
                B_DB_EXCP();
                if (pgmRtn) return;
            }
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.TRC(SCCGWA, CICACRC.COUNT);
                if (CICACRC.COUNT < K_MAX_ACRC_COUNT) {
                    CEP.TRC(SCCGWA, "WRITE ACR CASHE");
                    R000_WRITE_ACRC();
                    if (pgmRtn) return;
                }
            }
    } //FROM #ENDIF
        }
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        WS_BASC_I = 0;
        WS_BASC_FLG = ' ';
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        while (WS_BASC_I < CICBASC.COUNT 
            && WS_BASC_FLG != 'Y') {
            WS_BASC_I = (short) (WS_BASC_I + 1);
            CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
            if (CIRBAS.KEY.CI_NO.equalsIgnoreCase(CICBASC.DATA[WS_BASC_I-1].CI_NO)) {
                CEP.TRC(SCCGWA, "READ BAS CAHSE");
                R000_TRANS_BASC_TO_BAS();
                if (pgmRtn) return;
                WS_BASC_FLG = 'Y';
                SCCGWA.COMM_AREA.DBIO_FLG = '0';
            }
        }
    } //FROM #ENDIF
        if (WS_BASC_FLG != 'Y') {
            CEP.TRC(SCCGWA, "READ BAS TABLE");
            CITBAS_RD = new DBParm();
            CITBAS_RD.TableName = "CITBAS";
            IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.TRC(SCCGWA, CICBASC.COUNT);
                if (CICBASC.COUNT < K_MAX_BASC_COUNT) {
                    CEP.TRC(SCCGWA, "WRITE BAS CASHE");
                    R000_WRITE_BASC();
                    if (pgmRtn) return;
                }
            }
    } //FROM #ENDIF
        }
    }
    public void T000_STARTBR_CITACR_BY_ACNM() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.where = "AC_CNM LIKE :CIRACR.AC_CNM";
        IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_STARTBR_CITACR_BY_CINO() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        IBS.STARTBR(SCCGWA, CIRACR, CITACR_BR);
    }
    public void T000_READNEXT_CITACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_ENDBR_CITACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACR_BR);
    }
    public void T000_READ_CITACRL_BY_ACNO() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        CITACRL_RD.eqWhere = "AC_NO , AC_REL";
        CITACRL_RD.where = "( EXP_DT = 0 "
            + "OR EXP_DT > :SCCGWA.COMM_AREA.AC_DATE ) "
            + "AND SUBSTR ( REL_CTL , 1 , 1 ) = '1'";
        IBS.READ(SCCGWA, CIRACRL, this, CITACRL_RD);
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            WS_AC_ROUTING_FLG = 'N';
        } else {
            WS_AC_ROUTING_FLG = 'Y';
        }
    }
    public void S000_CALL_CIZQCHDC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CHDC", CICQCHDC);
        if (CICQCHDC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQCHDC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICQACRI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITNAM() throws IOException,SQLException,Exception {
        CITNAM_RD = new DBParm();
        CITNAM_RD.TableName = "CITNAM";
        CITNAM_RD.col = "CI_NM";
        IBS.READ(SCCGWA, CIRNAM, CITNAM_RD);
    }
    public void T000_READ_CITFLRL() throws IOException,SQLException,Exception {
        CITFLRL_RD = new DBParm();
        CITFLRL_RD.TableName = "CITFLRL";
        CITFLRL_RD.eqWhere = "AGR_NO";
        CITFLRL_RD.where = "REL_STS = '0'";
        CITFLRL_RD.fst = true;
        IBS.READ(SCCGWA, CIRFLRL, this, CITFLRL_RD);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
