package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZIFLTT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String K_PGM_NAME = "BPZIFLTT";
    String K_PARM_TRULE = "TRULE";
    String CPN_CALL_BPZPRMB = "BP-PARM-BROWSE      ";
    String CPN_CALL_BPZUFLT = "BP-UPD-FLT-STS      ";
    String WS_ERR_MSG = " ";
    int WS_EFF_DT = 0;
    int WS_EXP_DT = 0;
    short WS_CNT = 0;
    short WS_LEN = 0;
    char WS_FOUND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTRULE BPRTRULE = new BPRTRULE();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCUFLT BPCUFLT = new BPCUFLT();
    SCCGWA SCCGWA;
    BPCIFLTT BPCIFLTT;
    public void MP(SCCGWA SCCGWA, BPCIFLTT BPCIFLTT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCIFLTT = BPCIFLTT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZIFLTT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUFLT);
        IBS.init(SCCGWA, BPRTRULE);
        IBS.init(SCCGWA, BPRPRMT);
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NORMAL;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_BROWSE_FLT_CODE_INFO();
    }
    public void B010_BROWSE_FLT_CODE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMB);
        S000_TRANS_BPCTRULE();
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
    public void S000_TRANS_BPCTRULE() throws IOException,SQLException,Exception {
        BPRPRMT.KEY.TYP = K_PARM_TRULE;
        WS_LEN = 340;
        BPCPRMB.DAT_PTR = BPRPRMT;
    }
    public void T000_UPDATE_FLOAT_STATUS() throws IOException,SQLException,Exception {
        for (WS_CNT = 1; WS_CNT <= 10 
            && BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT-1].FLT_ITEM.trim().length() != 0; WS_CNT += 1) {
            CEP.TRC(SCCGWA, BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT-1].USE_FLG);
            CEP.TRC(SCCGWA, BPCIFLTT.TYPH_CODE);
            CEP.TRC(SCCGWA, BPRTRULE.KEY.CD);
            if (BPRTRULE.KEY.CD == null) BPRTRULE.KEY.CD = "";
            JIBS_tmp_int = BPRTRULE.KEY.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPRTRULE.KEY.CD += " ";
            if (BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT-1].USE_FLG == 'Y' 
                && BPRTRULE.KEY.CD.substring(0, 2).equalsIgnoreCase(BPCIFLTT.TYPH_CODE) 
                && BPRTRULE.KEY.CD.trim().length() > 0) {
                if (BPRTRULE.KEY.CD == null) BPRTRULE.KEY.CD = "";
                JIBS_tmp_int = BPRTRULE.KEY.CD.length();
                for (int i=0;i<40-JIBS_tmp_int;i++) BPRTRULE.KEY.CD += " ";
                BPCUFLT.FLT_CODE = BPRTRULE.KEY.CD.substring(11 - 1, 11 + 10 - 1);
                BPCUFLT.FLT_ITEM = BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT-1].FLT_ITEM;
                BPCUFLT.FLT_STS = BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT-1].FLT_RULE;
                BPCUFLT.TO_FLT = BPRTRULE.DATA_TXT.DAT_TXT[WS_CNT-1].TO_ITEM;
                CEP.TRC(SCCGWA, BPCUFLT.FLT_CODE);
                CEP.TRC(SCCGWA, BPCUFLT.FLT_ITEM);
                CEP.TRC(SCCGWA, BPCUFLT.FLT_STS);
                CEP.TRC(SCCGWA, BPCUFLT.TO_FLT);
                if (BPCUFLT.FLT_STS != 'B' 
                    && BPCUFLT.FLT_STS != 'D' 
                    && BPCUFLT.FLT_STS != 'T') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NRULE_ERR;
                    S000_ERR_MSG_PROC();
                }
                S000_CALL_BPZUFLT();
            }
        }
    }
    public void S000_CALL_BPZPRMB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CALL_BPZPRMB, BPCPRMB);
        CEP.TRC(SCCGWA, BPCPRMB.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUFLT.FLT_STS);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            BPRTRULE.KEY.TYP = BPRPRMT.KEY.TYP;
            BPRTRULE.KEY.CD = BPRPRMT.KEY.CD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRTRULE.DATA_TXT);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUFLT.FLT_STS);
        } else if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_FOUND_FLG = 'N';
        } else {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUFLT() throws IOException,SQLException,Exception {
        if (BPCIFLTT.FUNC == 'S') {
            BPCUFLT.FUNC = 'S';
        } else if (BPCIFLTT.FUNC == 'C') {
            BPCUFLT.FUNC = 'C';
        } else {
            BPCUFLT.FUNC = 'I';
        }
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
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
