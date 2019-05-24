package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSURTE {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_HIS_COPYBOOK_INTR = "BPRINTR";
    String K_HIS_COPYBOOK_RTFW = "BPRRTFW";
    String K_HIS_REMARKS = "BASE RATE MAINTENANCE";
    int WS_INTR_DATE = 0;
    BPZSURTE_WS_HIS_REF_NO WS_HIS_REF_NO = new BPZSURTE_WS_HIS_REF_NO();
    BPZSURTE_WS_HIS_REF_RTID WS_HIS_REF_RTID = new BPZSURTE_WS_HIS_REF_RTID();
    char WS_FILE_NAME = ' ';
    char WS_HIS_FLG = ' ';
    char WS_IHIT_FLG = ' ';
    char WS_INTR_REC_FOUND = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRINTR BPRINTR = new BPRINTR();
    BPRRTFW BPRRTFW = new BPRRTFW();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRINTR BPRBINTR = new BPRINTR();
    BPRRTFW BPRBRTFW = new BPRRTFW();
    BPCRINTM BPCRINTM = new BPCRINTM();
    BPCRINTB BPCRINTB = new BPCRINTB();
    BPCRHITM BPCRHITM = new BPCRHITM();
    BPCRHITB BPCRHITB = new BPCRHITB();
    BPCRMRTD BPCRMRTD = new BPCRMRTD();
    BPCRRTFW BPCRRTFW = new BPCRRTFW();
    BPCRHISM BPCRHISM = new BPCRHISM();
    SCCBSPS SCCBSPS = new SCCBSPS();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCSURTE BPCSURTE;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, BPCSURTE BPCSURTE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSURTE = BPCSURTE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSURTE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        BPCSURTE.BR = BPCRBANK.HEAD_BR;
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_UPDATE_INTR_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_UPDATE_INTR_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSURTE.BASE_TYP);
        CEP.TRC(SCCGWA, BPCSURTE.CCY);
        CEP.TRC(SCCGWA, BPCSURTE.TENOR);
        CEP.TRC(SCCGWA, BPCSURTE.EFF_DT);
        CEP.TRC(SCCGWA, BPCSURTE.RATE);
        if (BPCSURTE.EFF_DT > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, BPCRINTM);
            BPCRINTM.BR = BPCSURTE.BR;
            BPCRINTM.BASE_TYP = BPCSURTE.BASE_TYP;
            BPCRINTM.TENOR = BPCSURTE.TENOR;
            BPCRINTM.CCY = BPCSURTE.CCY;
            BPCRINTM.DT = BPCSURTE.EFF_DT;
            BPCRINTM.FUNC = 'R';
            S000_CALL_BPZRINTM();
            if (pgmRtn) return;
            if (BPCRINTM.RETURN_INFO == 'N') {
                B000_07_WRITE_INTR_PROC();
                if (pgmRtn) return;
                WS_HIS_FLG = 'A';
                WS_FILE_NAME = 'R';
                S020_HISTORY_PROCESS();
                if (pgmRtn) return;
                B030_01_UPDATE_IHIT_PROC();
                if (pgmRtn) return;
                B030_02_WRITE_IHIS_PROC();
                if (pgmRtn) return;
            } else {
                B000_08_MOVE_TO_BPRINTR();
                if (pgmRtn) return;
                B000_05_REWRITE_INTR_PROC();
                if (pgmRtn) return;
                WS_HIS_FLG = 'M';
                WS_IHIT_FLG = 'M';
                S020_HISTORY_PROCESS();
                if (pgmRtn) return;
                B030_01_UPDATE_IHIT_PROC();
                if (pgmRtn) return;
                B030_02_WRITE_IHIS_PROC();
                if (pgmRtn) return;
            }
        } else if (BPCSURTE.EFF_DT == SCCGWA.COMM_AREA.AC_DATE) {
            B000_04_READONLY_INTR_PROC();
            if (pgmRtn) return;
            if (WS_INTR_REC_FOUND == 'Y') {
                B030_UPD_INTR_HIIT_PROC();
                if (pgmRtn) return;
            } else {
                B030_ADD_INTR_HIIT_PROC();
                if (pgmRtn) return;
            }
        } else if (BPCSURTE.EFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
            B040_ADD_INTR_HIIT_PROC();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B030_UPD_INTR_HIIT_PROC() throws IOException,SQLException,Exception {
        B000_04_READUPD_INTR_PROC();
        if (pgmRtn) return;
        B000_08_MOVE_TO_BPRINTR();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, BPRINTR, BPRBINTR);
        B000_05_REWRITE_INTR_PROC();
        if (pgmRtn) return;
        B000_08_MOVE_TO_BPRINTR();
        if (pgmRtn) return;
        WS_IHIT_FLG = 'M';
        B030_01_UPDATE_IHIT_PROC();
        if (pgmRtn) return;
        B030_02_WRITE_IHIS_PROC();
        if (pgmRtn) return;
        WS_FILE_NAME = 'I';
        WS_HIS_FLG = 'M';
        S020_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B030_ADD_INTR_HIIT_PROC() throws IOException,SQLException,Exception {
        B000_07_WRITE_INTR_PROC();
        if (pgmRtn) return;
        WS_IHIT_FLG = 'A';
        B030_01_UPDATE_IHIT_PROC();
        if (pgmRtn) return;
        B030_02_WRITE_IHIS_PROC();
        if (pgmRtn) return;
        WS_FILE_NAME = 'I';
        WS_HIS_FLG = 'A';
        S020_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B040_ADD_INTR_HIIT_PROC() throws IOException,SQLException,Exception {
        B000_04_READONLY_INTR_PROC();
        if (pgmRtn) return;
        if (WS_INTR_REC_FOUND == 'N') {
            B000_07_WRITE_INTR_PROC();
            if (pgmRtn) return;
            WS_IHIT_FLG = 'A';
            WS_HIS_FLG = 'A';
        } else {
            WS_INTR_DATE = 0;
            WS_INTR_DATE = BPCRINTM.DT;
            B000_04_READUPD_INTR_PROC();
            if (pgmRtn) return;
            B000_08_MOVE_TO_BPRINTR();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, BPRINTR, BPRBINTR);
            B000_05_REWRITE_INTR_PROC();
            if (pgmRtn) return;
            B000_08_MOVE_TO_BPRINTR();
            if (pgmRtn) return;
            WS_IHIT_FLG = 'M';
            WS_HIS_FLG = 'M';
        }
        B030_01_UPDATE_IHIT_PROC();
        if (pgmRtn) return;
        B030_02_WRITE_IHIS_PROC();
        if (pgmRtn) return;
        if (WS_INTR_DATE > BPCSURTE.EFF_DT) {
            B030_06_DELETE_IHIT_PROC();
            if (pgmRtn) return;
        }
        WS_FILE_NAME = 'I';
        S020_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B030_06_DELETE_IHIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRHITB);
        BPCRHITB.FUNC = 'S';
        BPCRHITB.COND = 'A';
        BPCRHITB.STARTD = BPCSURTE.EFF_DT;
        BPCRHITB.BR = BPCSURTE.BR;
        BPCRHITB.CCY = BPCSURTE.CCY;
        BPCRHITB.BASE_TYP = BPCSURTE.BASE_TYP;
        BPCRHITB.TENOR = BPCSURTE.TENOR;
        S000_CALL_BPZRHITB();
        if (pgmRtn) return;
        while (BPCRHITB.RC.RC_CODE == 0) {
            BPCRHITB.FUNC = 'N';
            S000_CALL_BPZRHITB();
            if (pgmRtn) return;
            if (BPCRHITB.RC.RC_CODE == 0) {
                IBS.init(SCCGWA, BPCRHITM);
                BPCRHITM.BR = BPCRHITB.BR;
                BPCRHITM.CCY = BPCRHITB.CCY;
                BPCRHITM.BASE_TYP = BPCRHITB.BASE_TYP;
                BPCRHITM.TENOR = BPCRHITB.TENOR;
                BPCRHITM.DT = BPCRHITB.DT;
                BPCRHITM.FUNC = 'D';
                S000_CALL_BPZRHITM();
                if (pgmRtn) return;
            }
        }
        BPCRHITB.FUNC = 'E';
        S000_CALL_BPZRHITB();
        if (pgmRtn) return;
    }
    public void B030_01_UPDATE_IHIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRHITM);
        BPCRHITM.FUNC = 'R';
        BPCRHITM.BR = BPCSURTE.BR;
        BPCRHITM.CCY = BPCSURTE.CCY;
        BPCRHITM.BASE_TYP = BPCSURTE.BASE_TYP;
        BPCRHITM.TENOR = BPCSURTE.TENOR;
        BPCRHITM.DT = BPCSURTE.EFF_DT;
        S000_CALL_BPZRHITM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRHITM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST)) {
            IBS.init(SCCGWA, BPCRHITM);
            BPCRHITM.FUNC = 'A';
            BPCRHITM.BR = BPCSURTE.BR;
            BPCRHITM.CCY = BPCSURTE.CCY;
            BPCRHITM.BASE_TYP = BPCSURTE.BASE_TYP;
            BPCRHITM.TENOR = BPCSURTE.TENOR;
            BPCRHITM.DT = BPCSURTE.EFF_DT;
            BPCRHITM.TM = SCCGWA.COMM_AREA.TR_TIME;
            BPCRHITM.RATE = BPCSURTE.RATE;
            BPCRHITM.TELLER = SCCGWA.COMM_AREA.TL_ID;
            BPCRHITM.OVR1 = SCCGWA.COMM_AREA.SUP1_ID;
            BPCRHITM.OVR2 = SCCGWA.COMM_AREA.SUP2_ID;
            S000_CALL_BPZRHITM();
            if (pgmRtn) return;
            if (BPCRHITM.RC.RC_CODE > 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRHITM.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSURTE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            if (WS_IHIT_FLG == 'M') {
                BPCRHITM.FUNC = 'U';
                BPCRHITM.TM = SCCGWA.COMM_AREA.TR_TIME;
                BPCRHITM.RATE = BPCSURTE.RATE;
                BPCRHITM.TELLER = SCCGWA.COMM_AREA.TL_ID;
                BPCRHITM.OVR1 = SCCGWA.COMM_AREA.SUP1_ID;
                BPCRHITM.OVR2 = SCCGWA.COMM_AREA.SUP2_ID;
                S000_CALL_BPZRHITM();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_02_WRITE_IHIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRHISM);
        BPCRHISM.FUNC = 'A';
        BPCRHISM.BR = BPCSURTE.BR;
        BPCRHISM.CCY = BPCSURTE.CCY;
        BPCRHISM.BASE_TYP = BPCSURTE.BASE_TYP;
        BPCRHISM.TENOR = BPCSURTE.TENOR;
        BPCRHISM.DT = BPCSURTE.EFF_DT;
        BPCRHISM.TM = SCCGWA.COMM_AREA.TR_TIME;
        BPCRHISM.RATE = BPCSURTE.RATE;
        BPCRHISM.TELLER = SCCGWA.COMM_AREA.TL_ID;
        BPCRHISM.OVR1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPCRHISM.OVR2 = SCCGWA.COMM_AREA.SUP2_ID;
        CEP.TRC(SCCGWA, BPCRHISM);
        CEP.TRC(SCCGWA, BPCRHISM.RATE);
        CEP.TRC(SCCGWA, BPCRHISM.DT);
        CEP.TRC(SCCGWA, BPCRHISM.TM);
        S000_CALL_BPZRHISM();
        if (pgmRtn) return;
        if (BPCRHISM.RC.RC_CODE > 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRHISM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSURTE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B000_03_WRITE_RTFW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRRTFW);
        IBS.init(SCCGWA, BPRRTFW);
        BPRRTFW.KEY.BR = BPCSURTE.BR;
        BPRRTFW.KEY.BASE_TYP = BPCSURTE.BASE_TYP;
        BPRRTFW.KEY.TENOR = BPCSURTE.TENOR;
        BPRRTFW.KEY.CCY = BPCSURTE.CCY;
        BPRRTFW.KEY.VAL_DT = BPCSURTE.EFF_DT;
        BPRRTFW.RATE = BPCSURTE.RATE;
        BPRRTFW.DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRRTFW.TELLER = SCCGWA.COMM_AREA.TL_ID;
        BPRRTFW.OVR1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRRTFW.OVR2 = SCCGWA.COMM_AREA.SUP2_ID;
        BPCRRTFW.INFO.FUNC = 'C';
        BPCRRTFW.INFO.POINTER = BPRRTFW;
        BPCRRTFW.INFO.LEN = 128;
        S000_CALL_BPZRRTFW();
        if (pgmRtn) return;
        if (BPCRRTFW.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_DUPKEY, BPCSURTE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B000_04_READONLY_INTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRINTM);
        BPCRINTM.BR = BPCSURTE.BR;
        BPCRINTM.BASE_TYP = BPCSURTE.BASE_TYP;
        BPCRINTM.TENOR = BPCSURTE.TENOR;
        BPCRINTM.CCY = BPCSURTE.CCY;
        BPCRINTM.DT = BPCSURTE.EFF_DT;
        BPCRINTM.FUNC = 'I';
        S000_CALL_BPZRINTM();
        if (pgmRtn) return;
        if (BPCRINTM.RETURN_INFO == 'F') {
            WS_INTR_REC_FOUND = 'Y';
        } else {
            WS_INTR_REC_FOUND = 'N';
        }
    }
    public void B000_04_READUPD_INTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRINTM);
        BPCRINTM.BR = BPCSURTE.BR;
        BPCRINTM.BASE_TYP = BPCSURTE.BASE_TYP;
        BPCRINTM.TENOR = BPCSURTE.TENOR;
        BPCRINTM.CCY = BPCSURTE.CCY;
        BPCRINTM.DT = BPCSURTE.EFF_DT;
        CEP.TRC(SCCGWA, BPCRINTM.BR);
        CEP.TRC(SCCGWA, BPCRINTM.BASE_TYP);
        CEP.TRC(SCCGWA, BPCRINTM.TENOR);
        CEP.TRC(SCCGWA, BPCRINTM.CCY);
        BPCRINTM.FUNC = 'R';
        S000_CALL_BPZRINTM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRINTM.RC);
        CEP.TRC(SCCGWA, BPCRINTM.RETURN_INFO);
        if (BPCRINTM.RETURN_INFO != 'F') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRINTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSURTE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B000_05_REWRITE_INTR_PROC() throws IOException,SQLException,Exception {
        BPCRINTM.FUNC = 'U';
        BPCRINTM.DT = BPCSURTE.EFF_DT;
        BPCRINTM.TM = SCCGWA.COMM_AREA.TR_TIME;
        BPCRINTM.RATE = BPCSURTE.RATE;
        BPCRINTM.TELLER = SCCGWA.COMM_AREA.TL_ID;
        BPCRINTM.OVR1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPCRINTM.OVR2 = SCCGWA.COMM_AREA.SUP2_ID;
        S000_CALL_BPZRINTM();
        if (pgmRtn) return;
        if (BPCRINTM.RETURN_INFO != 'F') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRINTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSURTE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B000_06_REWRITE_RTFW_PROC() throws IOException,SQLException,Exception {
        BPCRRTFW.INFO.FUNC = 'U';
        BPRRTFW.RATE = BPCSURTE.RATE;
        BPRRTFW.DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRRTFW.TELLER = SCCGWA.COMM_AREA.TL_ID;
        BPRRTFW.OVR1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRRTFW.OVR2 = SCCGWA.COMM_AREA.SUP2_ID;
        BPCRRTFW.INFO.POINTER = BPRRTFW;
        BPCRRTFW.INFO.LEN = 128;
        S000_CALL_BPZRRTFW();
        if (pgmRtn) return;
    }
    public void B000_07_WRITE_INTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRINTM);
        BPCRINTM.BR = BPCSURTE.BR;
        BPCRINTM.BASE_TYP = BPCSURTE.BASE_TYP;
        BPCRINTM.TENOR = BPCSURTE.TENOR;
        BPCRINTM.CCY = BPCSURTE.CCY;
        BPCRINTM.DT = BPCSURTE.EFF_DT;
        BPCRINTM.TM = SCCGWA.COMM_AREA.TR_TIME;
        BPCRINTM.RATE = BPCSURTE.RATE;
        BPCRINTM.TELLER = SCCGWA.COMM_AREA.TL_ID;
        BPCRINTM.OVR1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPCRINTM.OVR2 = SCCGWA.COMM_AREA.SUP2_ID;
        BPCRINTM.FUNC = 'A';
        S000_CALL_BPZRINTM();
        if (pgmRtn) return;
        if (BPCRINTM.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_DUPKEY, BPCSURTE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B000_08_MOVE_TO_BPRINTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPCRINTM.BR;
        BPRINTR.KEY.BASE_TYP = BPCRINTM.BASE_TYP;
        BPRINTR.KEY.TENOR = BPCRINTM.TENOR;
        BPRINTR.KEY.CCY = BPCRINTM.CCY;
        BPRINTR.KEY.DT = BPCRINTM.DT;
        BPRINTR.TM = BPCRINTM.TM;
        BPRINTR.RATE = BPCRINTM.RATE;
        BPRINTR.TELLER = BPCRINTM.TELLER;
        BPRINTR.OVR1 = BPCRINTM.OVR1;
        BPRINTR.OVR2 = BPCRINTM.OVR2;
    }
    public void S000_CALL_BPZRMRTD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-RTID-MAINT", BPCRMRTD);
    }
    public void S000_CALL_BPZRRTFW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-RTFW-MAINT", BPCRRTFW);
    }
    public void S000_CALL_BPZRINTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-INTR-MAINT", BPCRINTM);
    }
    public void S000_CALL_BPZRHITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-IHIT-MAINT", BPCRHITM);
    }
    public void S000_CALL_BPZRHISM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-IHIS-MAINT", BPCRHISM);
    }
    public void S000_CALL_BPZRINTB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-INTR-BRW", BPCRINTB);
    }
    public void S020_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (WS_HIS_FLG == 'A' 
            || WS_HIS_FLG == 'D') {
            S020_01_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
        if (WS_HIS_FLG == 'M') {
            S020_02_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void S020_01_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        if (WS_HIS_FLG == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            if (WS_FILE_NAME == 'I') {
                BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_INTR;
                BPCPNHIS.INFO.TX_TYP_CD = "BPINTR";
            } else {
                BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_RTFW;
                BPCPNHIS.INFO.TX_TYP_CD = "BPRTFW";
            }
        }
        if (WS_HIS_FLG == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_RTFW;
            BPCPNHIS.INFO.TX_TYP_CD = "BPRTFW";
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        WS_HIS_REF_NO.WS_HIS_BR = BPCSURTE.BR;
        WS_HIS_REF_NO.WS_HIS_BASE_TYP = BPCSURTE.BASE_TYP;
        WS_HIS_REF_NO.WS_HIS_TENOR = BPCSURTE.TENOR;
        WS_HIS_REF_NO.WS_HIS_CCY = BPCSURTE.CCY;
        WS_HIS_REF_NO.WS_HIS_VAL_DT = BPCSURTE.EFF_DT;
        BPCPNHIS.INFO.REF_NO = IBS.CLS2CPY(SCCGWA, WS_HIS_REF_NO);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S020_02_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.TX_TYP = 'M';
        if (WS_FILE_NAME == 'I') {
            BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_INTR;
            BPCPNHIS.INFO.TX_TYP_CD = "BPINTR";
        } else {
            BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_RTFW;
            BPCPNHIS.INFO.TX_TYP_CD = "BPRTFW";
        }
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.FMT_ID);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        WS_HIS_REF_NO.WS_HIS_BR = BPCSURTE.BR;
        WS_HIS_REF_NO.WS_HIS_BASE_TYP = BPCSURTE.BASE_TYP;
        WS_HIS_REF_NO.WS_HIS_TENOR = BPCSURTE.TENOR;
        WS_HIS_REF_NO.WS_HIS_CCY = BPCSURTE.CCY;
        WS_HIS_REF_NO.WS_HIS_VAL_DT = BPCSURTE.EFF_DT;
        BPCPNHIS.INFO.REF_NO = IBS.CLS2CPY(SCCGWA, WS_HIS_REF_NO);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        if (WS_FILE_NAME == 'I') {
            BPCPNHIS.INFO.OLD_DAT_PT = BPRBINTR;
            BPCPNHIS.INFO.NEW_DAT_PT = BPRINTR;
        } else {
            BPCPNHIS.INFO.OLD_DAT_PT = BPRBRTFW;
            BPCPNHIS.INFO.NEW_DAT_PT = BPRRTFW;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_TXN_HIS, BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "HHH");
            CEP.TRC(SCCGWA, BPCPNHIS.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSURTE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRHITB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-IHIT-BRW", BPCRHITB);
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
