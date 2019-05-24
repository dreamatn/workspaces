package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZIFTLN {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String K_PGM_NAME = "BPZIFLTN";
    String K_PARM_FLTEF = "FLTEF";
    String K_PARM_NRULE = "NRULE";
    String CPN_CALL_BPZPCKWD = "BP-P-CHK-WORK-DAY   ";
    String CPN_CALL_BPZPQPCD = "BP-P-INQ-PC         ";
    String CPN_CALL_BPZPRMB = "BP-PARM-BROWSE      ";
    String CPN_CALL_BPZUFLT = "BP-UPD-FLT-STS      ";
    String CPN_CALL_BPZPXCAL = "BP-P-EX-INQ-CAL-CODE";
    String WS_ERR_MSG = " ";
    int WS_EFF_DT = 0;
    int WS_EXP_DT = 0;
    short WS_CNT = 0;
    char WS_FOUND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCNRULE BPCNRULE = new BPCNRULE();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPCOCKWD BPCOCKWD = new BPCOCKWD();
    BPCUFLT BPCUFLT = new BPCUFLT();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCOXCAL BPCOXCAL = new BPCOXCAL();
    SCCGWA SCCGWA;
    BPCIFLTN BPCIFLTN;
    public void MP(SCCGWA SCCGWA, BPCIFLTN BPCIFLTN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCIFLTN = BPCIFLTN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZIFTLN return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCKWD);
        IBS.init(SCCGWA, BPCUFLT);
        IBS.init(SCCGWA, BPCNRULE);
        IBS.init(SCCGWA, BPCOQPCD);
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NORMAL;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_GET_EXG_CAL_CODE();
        B020_CHECK_WORKDAY();
        CEP.TRC(SCCGWA, BPCOCKWD.SPD_DAY[1-1]);
        CEP.TRC(SCCGWA, BPCOCKWD.DAY_CHAR);
        B030_BROWSE_FLT_CODE_INFO();
    }
    public void B010_GET_EXG_CAL_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOXCAL);
        S000_CALL_BPZPXCAL();
    }
    public void B020_CHECK_WORKDAY() throws IOException,SQLException,Exception {
        BPCOCKWD.CAL_CODE = BPCOXCAL.CAL_CODE;
        BPCOCKWD.DATE = BPCIFLTN.DATE;
        CEP.TRC(SCCGWA, BPCOCKWD.DATE);
        BPCOCKWD.DAYE_FLG = 'Y';
        BPCOCKWD.STAT_FLG = 'Y';
        S000_CALL_BPZPCKWD();
        BPCIFLTN.TYPH_CODE = BPCOCKWD.DAY_CHAR;
    }
    public void B030_BROWSE_FLT_CODE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMB);
        S000_TRANS_BPCNRULE();
        BPCPRMB.FUNC = '0';
        S000_CALL_BPZPRMB();
        while (WS_FOUND_FLG != 'N') {
            BPCPRMB.FUNC = '1';
            S000_CALL_BPZPRMB();
            T000_UPDATE_FLOAT_STATUS();
        }
        BPCPRMB.FUNC = '2';
        S000_CALL_BPZPRMB();
    }
    public void S000_TRANS_BPCNRULE() throws IOException,SQLException,Exception {
        BPCNRULE.TYP = K_PARM_NRULE;
        BPCNRULE.REDEFINES3.FLT_CD = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = BPCNRULE.REDEFINES3.FLT_CD.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) BPCNRULE.REDEFINES3.FLT_CD = "0" + BPCNRULE.REDEFINES3.FLT_CD;
        BPCNRULE.CD = IBS.CLS2CPY(SCCGWA, BPCNRULE.REDEFINES3);
        BPCNRULE.DAT_LEN = 584;
        BPCPRMB.DAT_PTR = BPCNRULE;
    }
    public void T000_UPDATE_FLOAT_STATUS() throws IOException,SQLException,Exception {
        for (WS_CNT = 1; WS_CNT <= 10 
            && BPCNRULE.DATA.DAT_TXT[WS_CNT-1].FLT_ITEM.trim().length() != 0; WS_CNT += 1) {
            if (BPCOCKWD.SPD_DAY[1-1] != 0) {
                BPCUFLT.FLT_CODE = BPCNRULE.REDEFINES3.FLT_CD;
                BPCUFLT.FLT_ITEM = BPCNRULE.DATA.DAT_TXT[WS_CNT-1].FLT_ITEM;
                BPCUFLT.FLT_STS = BPCNRULE.DATA.DAT_TXT[WS_CNT-1].NOR_RULE;
                BPCUFLT.TO_FLT = BPCNRULE.DATA.DAT_TXT[WS_CNT-1].TO_ITEM1;
            } else {
                BPCUFLT.FLT_CODE = BPCNRULE.REDEFINES3.FLT_CD;
                BPCUFLT.FLT_ITEM = BPCNRULE.DATA.DAT_TXT[WS_CNT-1].FLT_ITEM;
                BPCUFLT.FLT_STS = BPCNRULE.DATA.DAT_TXT[WS_CNT-1].SPC_RULE;
                BPCUFLT.TO_FLT = BPCNRULE.DATA.DAT_TXT[WS_CNT-1].TO_ITEM2;
            }
            if (BPCUFLT.FLT_STS != 'B' 
                || BPCUFLT.FLT_STS != 'D' 
                || BPCUFLT.FLT_STS != 'T') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NRULE_ERR;
                S000_ERR_MSG_PROC();
            }
            if (BPCNRULE.DATA.DAT_TXT[WS_CNT-1].USE_FLG == 'Y' 
                && BPCNRULE.REDEFINES3.FLT_CD.trim().length() > 0) {
                if (BPCUFLT.FLT_STS == 'T') {
                    if (!BPCUFLT.TO_FLT.equalsIgnoreCase(BPCNRULE.DATA.DAT_TXT[WS_CNT-1].TO_ITEM1) 
                        || !BPCUFLT.TO_FLT.equalsIgnoreCase(BPCNRULE.DATA.DAT_TXT[WS_CNT-1].TO_ITEM2)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TO_ITEM_ERR;
                        S000_ERR_MSG_PROC();
                    }
                }
                S000_CALL_BPZUFLT();
            }
        }
    }
    public void S000_CALL_BPZPCKWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CALL_BPZPCKWD, BPCOCKWD);
        CEP.TRC(SCCGWA, BPCOCKWD.RC);
        if (BPCOCKWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCKWD.RC);
            S000_ERR_MSG_PROC();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NORMAL;
        }
    }
    public void S000_CALL_BPZPXCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CALL_BPZPXCAL, BPCOXCAL);
        CEP.TRC(SCCGWA, BPCOXCAL.RC);
        if (BPCOXCAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOXCAL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMB() throws IOException,SQLException,Exception {
        BPCPRMB.EFF_DT = WS_EFF_DT;
        IBS.CALLCPN(SCCGWA, CPN_CALL_BPZPRMB, BPCPRMB);
        CEP.TRC(SCCGWA, BPCPRMB.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOXCAL.RC.RC_CODE);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOXCAL.RC.RC_CODE);
        } else if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_FOUND_FLG = 'N';
        } else {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUFLT() throws IOException,SQLException,Exception {
        BPCUFLT.FUNC = 'I';
        IBS.CALLCPN(SCCGWA, CPN_CALL_BPZUFLT, BPCUFLT);
        CEP.TRC(SCCGWA, BPCUFLT.RC);
        if (BPCUFLT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUFLT.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCIFLTN.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCIFLTN = ");
            CEP.TRC(SCCGWA, BPCIFLTN);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
