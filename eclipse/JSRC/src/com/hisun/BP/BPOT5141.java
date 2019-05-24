package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5141 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String CPN_S_MNT_STAT_QTP = "BP-MNT-STAT-QTP  ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    int WS_I = 0;
    String WS_CCY = " ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCMSQ BPCMSQ = new BPCMSQ();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5140_AWA_5140 BPB5140_AWA_5140;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5141 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5140_AWA_5140>");
        BPB5140_AWA_5140 = (BPB5140_AWA_5140) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5140_AWA_5140.EXR_TYPE);
        if (BPB5140_AWA_5140.EXR_TYPE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_IN_EXR_TYPE;
            WS_FLD_NO = BPB5140_AWA_5140.EXR_TYPE_NO;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, BPREXRT);
            IBS.init(SCCGWA, BPCPRMR);
            BPREXRT.KEY.TYP = "EXRT";
            BPREXRT.KEY.CD = BPB5140_AWA_5140.EXR_TYPE;
            BPCPRMR.DAT_PTR = BPREXRT;
            S000_CALL_BPZPRMR();
            CEP.TRC(SCCGWA, BPCPRMR.RC.RC_RTNCODE);
            if (BPCPRMR.RC.RC_RTNCODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INVALID_EXR_TYP;
                WS_FLD_NO = BPB5140_AWA_5140.EXR_TYPE_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB5140_AWA_5140.STR_DATE == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_IN_DATE;
            WS_FLD_NO = BPB5140_AWA_5140.STR_DATE_NO;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB5140_AWA_5140.STR_DATE;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                if (WS_ERR_MSG == null) WS_ERR_MSG = "";
                JIBS_tmp_int = WS_ERR_MSG.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) WS_ERR_MSG += " ";
                WS_ERR_MSG = "SC" + WS_ERR_MSG.substring(2);
                if (WS_ERR_MSG == null) WS_ERR_MSG = "";
                JIBS_tmp_int = WS_ERR_MSG.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) WS_ERR_MSG += " ";
                JIBS_tmp_str[0] = "" + SCCCKDT.RC;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                WS_ERR_MSG = WS_ERR_MSG.substring(0, 3 - 1) + JIBS_tmp_str[0] + WS_ERR_MSG.substring(3 + 4 - 1);
                WS_FLD_NO = BPB5140_AWA_5140.STR_DATE_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_ADD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCMSQ);
        BPCMSQ.FUNC = 'A';
        BPCMSQ.EXR_TYP = BPB5140_AWA_5140.EXR_TYPE;
        BPCMSQ.STR_EFF_DATE = BPB5140_AWA_5140.STR_DATE;
        BPCMSQ.END_EFF_DATE = BPB5140_AWA_5140.END_DATE;
        CEP.TRC(SCCGWA, BPB5140_AWA_5140.EXR_TYPE);
        CEP.TRC(SCCGWA, BPB5140_AWA_5140.STR_DATE);
        CEP.TRC(SCCGWA, BPB5140_AWA_5140.END_DATE);
        for (WS_I = 1; WS_I <= 30; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPB5140_AWA_5140.QTP_DATA[WS_I-1].CCY);
            CEP.TRC(SCCGWA, BPB5140_AWA_5140.QTP_DATA[WS_I-1].UNIT);
            CEP.TRC(SCCGWA, BPB5140_AWA_5140.QTP_DATA[WS_I-1].RATE);
            BPCMSQ.QTP_DATA.DATA[WS_I-1].CCY = BPB5140_AWA_5140.QTP_DATA[WS_I-1].CCY;
            BPCMSQ.QTP_DATA.DATA[WS_I-1].UNIT = BPB5140_AWA_5140.QTP_DATA[WS_I-1].UNIT;
            BPCMSQ.QTP_DATA.DATA[WS_I-1].RATE = BPB5140_AWA_5140.QTP_DATA[WS_I-1].RATE;
        }
        S00_CALL_BPZMSQ();
    }
    public void S00_CALL_BPZMSQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_MNT_STAT_QTP, BPCMSQ);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
