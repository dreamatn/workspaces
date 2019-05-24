package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZQFLT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZQFLT";
    String K_PARM_FLOAT = "FLOAT";
    String K_PARM_DESC = "FLOAT MAINTAIN   ";
    String K_PARM_CDESC = "FLOAT MAINTAIN   ";
    String CPN_CALL_BPZPQPCD = "BP-P-INQ-PC     ";
    String CPN_CALL_BPZPRMM = "BP-PARM-MAINTAIN    ";
    String CPN_CALL_BPZPRMB = "BP-PARM-BROWSE      ";
    String WS_ERR_MSG = " ";
    int WS_EFF_DT = 0;
    int WS_EXP_DT = 0;
    short WS_CNT = 0;
    short WS_I = 0;
    char CCY_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCFLOAT BPCFLOAT = new BPCFLOAT();
    SCCGWA SCCGWA;
    BPCQFLT BPCQFLT;
    public void MP(SCCGWA SCCGWA, BPCQFLT BPCQFLT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCQFLT = BPCQFLT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZQFLT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPCOQPCD);
        IBS.init(SCCGWA, BPCFLOAT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_PCD();
        if (pgmRtn) return;
        if (BPCQFLT.FLT_CODE.trim().length() > 0 
            && BPCQFLT.FLT_ITEM.trim().length() > 0) {
            B020_INQUIRE_FLT_CODE_INFO();
            if (pgmRtn) return;
        } else {
            B030_BROWSE_FLT_CODE_INFO();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_PCD() throws IOException,SQLException,Exception {
        if (BPCQFLT.FLT_ITEM.trim().length() > 0 
            && BPCQFLT.FLT_CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FLT_CODE_MUST_INPUT, BPCQFLT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCQFLT.FLT_CODE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCOQPCD);
            BPCOQPCD.INPUT_DATA.TYPE = "FLT-T";
            BPCOQPCD.INPUT_DATA.CODE = BPCQFLT.FLT_CODE;
            S000_CALL_BPZPQPCD();
            if (pgmRtn) return;
        }
        if (BPCQFLT.FLT_ITEM.trim().length() > 0) {
            IBS.init(SCCGWA, BPCOQPCD);
            BPCOQPCD.INPUT_DATA.TYPE = "FLT-D";
            BPCOQPCD.INPUT_DATA.CODE = BPCQFLT.FLT_ITEM;
            S000_CALL_BPZPQPCD();
            if (pgmRtn) return;
        }
    }
    public void B020_INQUIRE_FLT_CODE_INFO() throws IOException,SQLException,Exception {
        S000_TRANS_BPCFLOAT();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        if (CCY_FLG == 'N' 
            && WS_FOUND_FLG == 'N') {
            CCY_FLG = 'S';
            if (BPCFLOAT.KEY.CD == null) BPCFLOAT.KEY.CD = "";
            JIBS_tmp_int = BPCFLOAT.KEY.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCFLOAT.KEY.CD += " ";
            BPCFLOAT.KEY.CD = BPCFLOAT.KEY.CD.substring(0, 21 - 1) + "   " + BPCFLOAT.KEY.CD.substring(21 + 3 - 1);
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
        }
        S000_TRANS_DATA_OUT();
        if (pgmRtn) return;
    }
    public void B030_BROWSE_FLT_CODE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMB);
        S000_TRANS_BPCFLOAT();
        if (pgmRtn) return;
        BPCPRMB.FUNC = '0';
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        while (WS_FOUND_FLG != 'N') {
            BPCPRMB.FUNC = '1';
            S000_CALL_BPZPRMB();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
                if (BPCQFLT.FLT_CODE.trim().length() == 0) {
                    S000_TRANS_DATA_OUT();
                    if (pgmRtn) return;
                } else {
                    if (BPCFLOAT.KEY.CD == null) BPCFLOAT.KEY.CD = "";
                    JIBS_tmp_int = BPCFLOAT.KEY.CD.length();
                    for (int i=0;i<40-JIBS_tmp_int;i++) BPCFLOAT.KEY.CD += " ";
                    if (BPCQFLT.FLT_CODE.equalsIgnoreCase(BPCFLOAT.KEY.CD.substring(0, 10))) {
                        S000_TRANS_DATA_OUT();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        BPCPRMB.FUNC = '2';
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
    }
    public void S000_TRANS_BPCFLOAT() throws IOException,SQLException,Exception {
        BPCQFLT.OUTPUT_DATA.FLT_CNT = 0;
        BPCFLOAT.KEY.TYP = "FLOAT";
        if (BPCFLOAT.KEY.CD == null) BPCFLOAT.KEY.CD = "";
        JIBS_tmp_int = BPCFLOAT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCFLOAT.KEY.CD += " ";
        if (BPCQFLT.FLT_CODE == null) BPCQFLT.FLT_CODE = "";
        JIBS_tmp_int = BPCQFLT.FLT_CODE.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCQFLT.FLT_CODE += " ";
        BPCFLOAT.KEY.CD = BPCQFLT.FLT_CODE + BPCFLOAT.KEY.CD.substring(10);
        if (BPCFLOAT.KEY.CD == null) BPCFLOAT.KEY.CD = "";
        JIBS_tmp_int = BPCFLOAT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCFLOAT.KEY.CD += " ";
        if (BPCQFLT.FLT_ITEM == null) BPCQFLT.FLT_ITEM = "";
        JIBS_tmp_int = BPCQFLT.FLT_ITEM.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCQFLT.FLT_ITEM += " ";
        BPCFLOAT.KEY.CD = BPCFLOAT.KEY.CD.substring(0, 11 - 1) + BPCQFLT.FLT_ITEM + BPCFLOAT.KEY.CD.substring(11 + 10 - 1);
        if (BPCFLOAT.KEY.CD == null) BPCFLOAT.KEY.CD = "";
        JIBS_tmp_int = BPCFLOAT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCFLOAT.KEY.CD += " ";
        if (BPCQFLT.CCY == null) BPCQFLT.CCY = "";
        JIBS_tmp_int = BPCQFLT.CCY.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCQFLT.CCY += " ";
        BPCFLOAT.KEY.CD = BPCFLOAT.KEY.CD.substring(0, 21 - 1) + BPCQFLT.CCY + BPCFLOAT.KEY.CD.substring(21 + 3 - 1);
        if (BPCQFLT.CCY.trim().length() > 0) {
            CCY_FLG = 'N';
        }
        BPCFLOAT.DESC = K_PARM_DESC;
        BPCFLOAT.CDESC = K_PARM_CDESC;
        WS_EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCFLOAT.DAT_LEN = 142;
        if (BPCQFLT.FLT_ITEM.trim().length() == 0) {
            BPCPRMB.DAT_PTR = BPCFLOAT;
        } else {
            BPCPRMM.DAT_PTR = BPCFLOAT;
        }
    }
    public void S000_TRANS_DATA_OUT() throws IOException,SQLException,Exception {
        BPCQFLT.OUTPUT_DATA.FLT_CNT = (short) (BPCQFLT.OUTPUT_DATA.FLT_CNT + 1);
        CEP.TRC(SCCGWA, BPCQFLT.OUTPUT_DATA.FLT_CNT);
        if (BPCFLOAT.KEY.CD == null) BPCFLOAT.KEY.CD = "";
        JIBS_tmp_int = BPCFLOAT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCFLOAT.KEY.CD += " ";
        BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].FLT_CD = BPCFLOAT.KEY.CD.substring(0, 10);
        CEP.TRC(SCCGWA, BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].FLT_CD);
        if (BPCFLOAT.KEY.CD == null) BPCFLOAT.KEY.CD = "";
        JIBS_tmp_int = BPCFLOAT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCFLOAT.KEY.CD += " ";
        BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].FLT_ITM = BPCFLOAT.KEY.CD.substring(11 - 1, 11 + 10 - 1);
        CEP.TRC(SCCGWA, BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].FLT_ITM);
        if (BPCFLOAT.KEY.CD == null) BPCFLOAT.KEY.CD = "";
        JIBS_tmp_int = BPCFLOAT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCFLOAT.KEY.CD += " ";
        BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].FLT_CCY = BPCFLOAT.KEY.CD.substring(21 - 1, 21 + 3 - 1);
        CEP.TRC(SCCGWA, BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].FLT_CCY);
        BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].STS = BPCFLOAT.DAT_TXT.FLT_RULE;
        CEP.TRC(SCCGWA, BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].STS);
        BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].TO_FLT = BPCFLOAT.DAT_TXT.TO_ITEM;
        CEP.TRC(SCCGWA, BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].TO_FLT);
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = "FLT-T";
        CEP.TRC(SCCGWA, "KKK");
        CEP.TRC(SCCGWA, BPCQFLT.FLT_CODE);
        CEP.TRC(SCCGWA, BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].FLT_CD);
        if (BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].FLT_CD == null) BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].FLT_CD = "";
        JIBS_tmp_int = BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].FLT_CD.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].FLT_CD += " ";
        for (WS_I = 1; BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].FLT_CD.substring(WS_I - 1, WS_I + 1 - 1).trim().length() <= 0; WS_I += 1) {
        }
        BPCOQPCD.INPUT_DATA.CODE = BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].FLT_CD.substring(WS_I-1);
        CEP.TRC(SCCGWA, BPCOQPCD.INPUT_DATA.CODE);
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
        BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].FLT_CD_DES = BPCOQPCD.OUTPUT_DATA.CODE_INFO.ENG_NAME;
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = "FLT-D";
        if (BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].FLT_ITM == null) BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].FLT_ITM = "";
        JIBS_tmp_int = BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].FLT_ITM.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].FLT_ITM += " ";
        for (WS_I = 1; BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].FLT_ITM.substring(WS_I - 1, WS_I + 1 - 1).trim().length() <= 0; WS_I += 1) {
        }
        BPCOQPCD.INPUT_DATA.CODE = BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].FLT_ITM.substring(WS_I-1);
        CEP.TRC(SCCGWA, BPCOQPCD.INPUT_DATA.CODE);
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
        BPCQFLT.OUTPUT_DATA.DATA[BPCQFLT.OUTPUT_DATA.FLT_CNT-1].FLT_ITM_DES = BPCOQPCD.OUTPUT_DATA.CODE_INFO.ENG_NAME;
        CEP.TRC(SCCGWA, BPCOQPCD.OUTPUT_DATA.CODE_INFO.ENG_NAME);
        CEP.TRC(SCCGWA, BPCQFLT.OUTPUT_DATA);
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CALL_BPZPQPCD, BPCOQPCD);
        CEP.TRC(SCCGWA, BPCOQPCD.RC);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCQFLT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        BPCPRMM.EFF_DT = WS_EFF_DT;
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        CEP.TRC(SCCGWA, BPCPRMM.EXP_DT);
        CEP.TRC(SCCGWA, BPCFLOAT.KEY.TYP);
        CEP.TRC(SCCGWA, BPCFLOAT.KEY.CD);
        IBS.CALLCPN(SCCGWA, CPN_CALL_BPZPRMM, BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC.RC_CODE);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCQFLT.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC.RC_CODE);
        } else if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCQFLT.RC);
            WS_FOUND_FLG = 'N';
            if (CCY_FLG == 'S') {
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCQFLT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMB() throws IOException,SQLException,Exception {
        BPCPRMB.EFF_DT = WS_EFF_DT;
        IBS.CALLCPN(SCCGWA, CPN_CALL_BPZPRMB, BPCPRMB);
        CEP.TRC(SCCGWA, BPCPRMB.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CCY_FLG);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCQFLT.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CCY_FLG);
        } else if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_FOUND_FLG = 'N';
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCQFLT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCQFLT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCQFLT = ");
            CEP.TRC(SCCGWA, BPCQFLT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
