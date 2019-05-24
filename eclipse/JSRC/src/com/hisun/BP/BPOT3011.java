package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3011 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    int WS_I = 0;
    int WS_OUT_LOC = 0;
    int WS_BVNO_LEN = 0;
    long WS_COMP_BEGNO = 0;
    long WS_COMP_ENDNO = 0;
    long WS_BEG_NO = 0;
    long WS_END_NO = 0;
    int WS_NUM = 0;
    long WS_BEG_RESULT = 0;
    long WS_END_RESULT = 0;
    long WS_NUM_RESULT = 0;
    BPOT3011_WS_OUT_DATA WS_OUT_DATA = new BPOT3011_WS_OUT_DATA();
    BPOT3011_WS_MSGID WS_MSGID = new BPOT3011_WS_MSGID();
    short WS_FLD_NO = 0;
    BPCOOTLS BPCOOTLS = new BPCOOTLS();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPRMSG BPRRMSG = new BPRMSG();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    SCCGWA SCCGWA;
    BPB3010_AWA_3010 BPB3010_AWA_3010;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPOT3011 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3010_AWA_3010>");
        BPB3010_AWA_3010 = (BPB3010_AWA_3010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_DEAL_PROCESS();
        B300_OUTPUT_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO);
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].END_NO);
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].NUM);
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].CODE);
        if ((BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.trim().length() == 0 
            && BPB3010_AWA_3010.BV_DATA[1-1].END_NO.trim().length() == 0) 
            || (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.trim().length() == 0 
            && BPB3010_AWA_3010.BV_DATA[1-1].NUM == 0) 
            || (BPB3010_AWA_3010.BV_DATA[1-1].END_NO.trim().length() == 0 
            && BPB3010_AWA_3010.BV_DATA[1-1].NUM == 0)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MST_IPT_TWO_FLD, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPB3010_AWA_3010.BV_DATA[1-1].CODE;
        S000_CALL_BPZFBVQU();
        if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.trim().length() > 0) {
            if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO == null) BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO = "";
            JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO += " ";
            for (WS_I = 1; IBS.isNumeric(BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1 
                || BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.substring(WS_I-1).trim().length() > 0) {
                CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO);
                CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.substring(WS_I-1));
                CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
                CEP.TRC(SCCGWA, WS_I);
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN, WS_MSGID);
                WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB3010_AWA_3010.BV_DATA[1-1].END_NO.trim().length() > 0) {
            if (BPB3010_AWA_3010.BV_DATA[1-1].END_NO == null) BPB3010_AWA_3010.BV_DATA[1-1].END_NO = "";
            JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[1-1].END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[1-1].END_NO += " ";
            for (WS_I = 1; IBS.isNumeric(BPB3010_AWA_3010.BV_DATA[1-1].END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1 
                || BPB3010_AWA_3010.BV_DATA[1-1].END_NO.substring(WS_I-1).trim().length() > 0) {
                CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].END_NO);
                CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].END_NO.substring(WS_I-1));
                CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
                CEP.TRC(SCCGWA, WS_I);
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN, WS_MSGID);
                WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[1-1].END_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
        if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.trim().length() > 0 
            && BPB3010_AWA_3010.BV_DATA[1-1].END_NO.trim().length() > 0) {
            if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO == null) BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO = "";
            JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO += " ";
            if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
            else WS_COMP_BEGNO = Long.parseLong(BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.substring(0, WS_BVNO_LEN));
            if (BPB3010_AWA_3010.BV_DATA[1-1].END_NO == null) BPB3010_AWA_3010.BV_DATA[1-1].END_NO = "";
            JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[1-1].END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[1-1].END_NO += " ";
            if (BPB3010_AWA_3010.BV_DATA[1-1].END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
            else WS_COMP_ENDNO = Long.parseLong(BPB3010_AWA_3010.BV_DATA[1-1].END_NO.substring(0, WS_BVNO_LEN));
            if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BEG_END, WS_MSGID);
                WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B200_DEAL_PROCESS() throws IOException,SQLException,Exception {
        if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO == null) BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO = "";
        JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO += " ";
        if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_BEG_NO = 0;
        else WS_BEG_NO = Long.parseLong(BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.substring(0, WS_BVNO_LEN));
        if (BPB3010_AWA_3010.BV_DATA[1-1].END_NO == null) BPB3010_AWA_3010.BV_DATA[1-1].END_NO = "";
        JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[1-1].END_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[1-1].END_NO += " ";
        if (BPB3010_AWA_3010.BV_DATA[1-1].END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_END_NO = 0;
        else WS_END_NO = Long.parseLong(BPB3010_AWA_3010.BV_DATA[1-1].END_NO.substring(0, WS_BVNO_LEN));
        WS_NUM = BPB3010_AWA_3010.BV_DATA[1-1].NUM;
        CEP.TRC(SCCGWA, WS_BEG_NO);
        CEP.TRC(SCCGWA, WS_END_NO);
        CEP.TRC(SCCGWA, WS_NUM);
        if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.trim().length() == 0 
            && WS_END_NO < WS_NUM) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NUM_CANNOT_LAR_END, WS_MSGID);
            WS_FLD_NO = (short) BPB3010_AWA_3010.BV_DATA[1-1].NUM;
            S000_ERR_MSG_PROC();
        }
        if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.trim().length() > 0 
            && BPB3010_AWA_3010.BV_DATA[1-1].END_NO.trim().length() > 0 
            && BPB3010_AWA_3010.BV_DATA[1-1].NUM != 0) {
            CEP.TRC(SCCGWA, "INPUT THREE FIELDS THEN CALCULATE NUM");
            WS_NUM_RESULT = WS_END_NO - WS_BEG_NO + 1;
        }
        if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.trim().length() > 0 
            && BPB3010_AWA_3010.BV_DATA[1-1].END_NO.trim().length() > 0 
            && BPB3010_AWA_3010.BV_DATA[1-1].NUM == 0) {
            CEP.TRC(SCCGWA, "CALCULATE NUM");
            WS_NUM_RESULT = WS_END_NO - WS_BEG_NO + 1;
        }
        if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.trim().length() == 0 
            && BPB3010_AWA_3010.BV_DATA[1-1].END_NO.trim().length() > 0 
            && BPB3010_AWA_3010.BV_DATA[1-1].NUM != 0) {
            CEP.TRC(SCCGWA, "CALCULATE BEG-NO");
            WS_BEG_RESULT = WS_END_NO - WS_NUM + 1;
        }
        if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.trim().length() > 0 
            && BPB3010_AWA_3010.BV_DATA[1-1].END_NO.trim().length() == 0 
            && BPB3010_AWA_3010.BV_DATA[1-1].NUM != 0) {
            CEP.TRC(SCCGWA, "CALCULATE END-NO");
            WS_END_RESULT = WS_BEG_NO + WS_NUM - 1;
        }
        CEP.TRC(SCCGWA, WS_BEG_RESULT);
        CEP.TRC(SCCGWA, WS_END_RESULT);
        CEP.TRC(SCCGWA, WS_NUM_RESULT);
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_DATA);
        IBS.init(SCCGWA, SCCFMT);
        WS_OUT_LOC = 18 - WS_BVNO_LEN + 1;
        JIBS_tmp_str[0] = "" + WS_BEG_RESULT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<20-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_OUT_DATA.WS_OUT_BEG = JIBS_tmp_str[0].substring(WS_OUT_LOC - 1, WS_OUT_LOC + WS_BVNO_LEN - 1);
        JIBS_tmp_str[0] = "" + WS_END_RESULT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<20-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_OUT_DATA.WS_OUT_END = JIBS_tmp_str[0].substring(WS_OUT_LOC - 1, WS_OUT_LOC + WS_BVNO_LEN - 1);
        WS_OUT_DATA.WS_OUT_NUM = (int) WS_NUM_RESULT;
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_BEG);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_END);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_NUM);
        SCCFMT.FMTID = "BP573";
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 49;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-BV-PRM-QUERY", BPCFBVQU);
        CEP.TRC(SCCGWA, BPCFBVQU.RC);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
