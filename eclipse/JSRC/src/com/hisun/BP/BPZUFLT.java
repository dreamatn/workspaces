package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUFLT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZUFLT";
    String K_PARM_FLOAT = "FLOAT";
    String K_PARM_DESC = "FLOAT MAINTAIN   ";
    String K_PARM_CDESC = "FLOAT MAINTAIN   ";
    String CPN_CALL_BPZPRMM = "BP-PARM-MAINTAIN    ";
    int WS_EFF_DT = 0;
    int WS_EXP_DT = 0;
    short WS_CNT = 0;
    char WS_FOUND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCFLOAT BPCFLOAT = new BPCFLOAT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPCUFLT BPCUFLT;
    public void MP(SCCGWA SCCGWA, BPCUFLT BPCUFLT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUFLT = BPCUFLT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUFLT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPCFLOAT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_UPDATE_FLOAT_STATUS();
        if (pgmRtn) return;
    }
    public void B010_UPDATE_FLOAT_STATUS() throws IOException,SQLException,Exception {
        T000_TRANS_BPCFLOAT();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '4';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        T000_SET_FLOAT_STATUS();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'N') {
            CEP.TRC(SCCGWA, "ADD A NEW STATUS");
            CEP.TRC(SCCGWA, BPCUFLT.FLT_CODE);
            BPCPRMM.FUNC = '0';
        } else {
            BPCPRMM.FUNC = '2';
        }
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void T000_TRANS_BPCFLOAT() throws IOException,SQLException,Exception {
        BPCFLOAT.KEY.TYP = "FLOAT";
        if (BPCFLOAT.KEY.CD == null) BPCFLOAT.KEY.CD = "";
        JIBS_tmp_int = BPCFLOAT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCFLOAT.KEY.CD += " ";
        if (BPCUFLT.FLT_CODE == null) BPCUFLT.FLT_CODE = "";
        JIBS_tmp_int = BPCUFLT.FLT_CODE.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCUFLT.FLT_CODE += " ";
        BPCFLOAT.KEY.CD = BPCUFLT.FLT_CODE + BPCFLOAT.KEY.CD.substring(10);
        if (BPCFLOAT.KEY.CD == null) BPCFLOAT.KEY.CD = "";
        JIBS_tmp_int = BPCFLOAT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCFLOAT.KEY.CD += " ";
        if (BPCUFLT.FLT_ITEM == null) BPCUFLT.FLT_ITEM = "";
        JIBS_tmp_int = BPCUFLT.FLT_ITEM.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCUFLT.FLT_ITEM += " ";
        BPCFLOAT.KEY.CD = BPCFLOAT.KEY.CD.substring(0, 11 - 1) + BPCUFLT.FLT_ITEM + BPCFLOAT.KEY.CD.substring(11 + 10 - 1);
        if (BPCFLOAT.KEY.CD == null) BPCFLOAT.KEY.CD = "";
        JIBS_tmp_int = BPCFLOAT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCFLOAT.KEY.CD += " ";
        if (BPCUFLT.CCY == null) BPCUFLT.CCY = "";
        JIBS_tmp_int = BPCUFLT.CCY.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCUFLT.CCY += " ";
        BPCFLOAT.KEY.CD = BPCFLOAT.KEY.CD.substring(0, 21 - 1) + BPCUFLT.CCY + BPCFLOAT.KEY.CD.substring(21 + 3 - 1);
        CEP.TRC(SCCGWA, BPCUFLT.CCY);
        BPCFLOAT.DESC = K_PARM_DESC;
        BPCFLOAT.CDESC = K_PARM_CDESC;
        WS_EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        WS_EXP_DT = 99991231;
    }
    public void T000_SET_FLOAT_STATUS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUFLT.FUNC);
        CEP.TRC(SCCGWA, BPCUFLT.FLT_CODE);
        CEP.TRC(SCCGWA, BPCUFLT.FLT_STS);
        CEP.TRC(SCCGWA, BPCUFLT.TO_FLT);
        CEP.TRC(SCCGWA, BPCFLOAT.DAT_TXT.FLT_RULE);
        CEP.TRC(SCCGWA, BPCFLOAT.DAT_TXT.TO_ITEM);
        CEP.TRC(SCCGWA, BPCFLOAT.DAT_TXT.OLD_FLT_RULE);
        CEP.TRC(SCCGWA, BPCFLOAT.DAT_TXT.OLD_TO_ITEM);
        if (BPCUFLT.FUNC == 'I') {
            BPCFLOAT.DAT_TXT.FLT_RULE = BPCUFLT.FLT_STS;
            BPCFLOAT.DAT_TXT.TO_ITEM = BPCUFLT.TO_FLT;
            BPCFLOAT.DAT_TXT.OLD_FLT_RULE = ' ';
            BPCFLOAT.DAT_TXT.OLD_TO_ITEM = " ";
        } else if (BPCUFLT.FUNC == 'R') {
            if (BPCFLOAT.DAT_TXT.FLT_RULE == 'O') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FLOAT_RELASED, BPCUFLT.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "1111111");
                BPCFLOAT.DAT_TXT.OLD_FLT_RULE = BPCFLOAT.DAT_TXT.FLT_RULE;
                BPCFLOAT.DAT_TXT.OLD_TO_ITEM = BPCFLOAT.DAT_TXT.TO_ITEM;
                BPCFLOAT.DAT_TXT.FLT_RULE = BPCUFLT.FLT_STS;
                BPCFLOAT.DAT_TXT.TO_ITEM = BPCUFLT.TO_FLT;
            }
        } else if (BPCUFLT.FUNC == 'O') {
            if (BPCFLOAT.DAT_TXT.FLT_RULE != 'O') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FLOAT_NOT_RELASED, BPCUFLT.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "2222222");
                BPCFLOAT.DAT_TXT.FLT_RULE = BPCFLOAT.DAT_TXT.OLD_FLT_RULE;
                BPCUFLT.FLT_STS = BPCFLOAT.DAT_TXT.OLD_FLT_RULE;
                BPCFLOAT.DAT_TXT.TO_ITEM = BPCFLOAT.DAT_TXT.OLD_TO_ITEM;
                BPCUFLT.TO_FLT = BPCFLOAT.DAT_TXT.OLD_TO_ITEM;
            }
        } else if (BPCUFLT.FUNC == 'S') {
            if (BPCFLOAT.DAT_TXT.FLT_RULE != 'O') {
                CEP.TRC(SCCGWA, "FFFFFFF");
                CEP.TRC(SCCGWA, BPCFLOAT.DAT_TXT.FLT_RULE);
                CEP.TRC(SCCGWA, BPCFLOAT.DAT_TXT.TO_ITEM);
                CEP.TRC(SCCGWA, BPCUFLT.FLT_STS);
                CEP.TRC(SCCGWA, BPCUFLT.FLT_STS);
                BPCFLOAT.DAT_TXT.OLD_FLT_RULE = BPCFLOAT.DAT_TXT.FLT_RULE;
                BPCFLOAT.DAT_TXT.OLD_TO_ITEM = BPCFLOAT.DAT_TXT.TO_ITEM;
                CEP.TRC(SCCGWA, BPCFLOAT.DAT_TXT.OLD_FLT_RULE);
                CEP.TRC(SCCGWA, BPCFLOAT.DAT_TXT.OLD_TO_ITEM);
                BPCFLOAT.DAT_TXT.FLT_RULE = BPCUFLT.FLT_STS;
                BPCFLOAT.DAT_TXT.TO_ITEM = BPCUFLT.TO_FLT;
            }
        } else if (BPCUFLT.FUNC == 'C') {
            if (BPCFLOAT.DAT_TXT.FLT_RULE != 'O') {
                CEP.TRC(SCCGWA, "3333333");
                BPCFLOAT.DAT_TXT.FLT_RULE = BPCFLOAT.DAT_TXT.OLD_FLT_RULE;
                BPCFLOAT.DAT_TXT.TO_ITEM = BPCFLOAT.DAT_TXT.OLD_TO_ITEM;
                BPCUFLT.FLT_STS = BPCFLOAT.DAT_TXT.OLD_FLT_RULE;
                BPCUFLT.TO_FLT = BPCFLOAT.DAT_TXT.OLD_TO_ITEM;
            }
        } else {
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        BPCFLOAT.DAT_LEN = 142;
        BPCPRMM.DAT_PTR = BPCFLOAT;
        BPCPRMM.EFF_DT = WS_EFF_DT;
        BPCPRMM.EXP_DT = WS_EXP_DT;
        IBS.CALLCPN(SCCGWA, CPN_CALL_BPZPRMM, BPCPRMM);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFLOAT.DAT_TXT.FLT_RULE);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUFLT.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFLOAT.DAT_TXT.FLT_RULE);
        } else if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_FOUND_FLG = 'N';
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFLOAT.DAT_TXT.FLT_RULE);
        } else if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_ALEADY_EXIST)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUFLT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUFLT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUFLT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUFLT = ");
            CEP.TRC(SCCGWA, BPCUFLT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
