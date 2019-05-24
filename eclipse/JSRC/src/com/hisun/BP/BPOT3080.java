package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3080 {
    int JIBS_tmp_int;
    String CPN_S_BV_DESTROY = "BP-S-BV-DESTROY ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_S_NUM_CHK = "BP-S-BV-NO-CHK";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_BVNO_LEN = 0;
    long WS_COMP_BEGNO = 0;
    long WS_COMP_ENDNO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBVDE BPCSBVDE = new BPCSBVDE();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCSNOCK BPCSNOCK = new BPCSNOCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3020_AWA_3020 BPB3020_AWA_3020;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3080 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3020_AWA_3020>");
        BPB3020_AWA_3020 = (BPB3020_AWA_3020) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPB3020_AWA_3020.DE_TYP == 'N') {
            B010_CHECK_INPUT();
        }
        B020_BV_DESTROY();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE;
        S000_CALL_BPZFBVQU();
        if (BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.trim().length() > 0) {
            if (BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO == null) BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO = "";
            JIBS_tmp_int = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                WS_FLD_NO = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB3020_AWA_3020.BV_DATA[1-1].END_NO.trim().length() > 0) {
            if (BPB3020_AWA_3020.BV_DATA[1-1].END_NO == null) BPB3020_AWA_3020.BV_DATA[1-1].END_NO = "";
            JIBS_tmp_int = BPB3020_AWA_3020.BV_DATA[1-1].END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3020_AWA_3020.BV_DATA[1-1].END_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPB3020_AWA_3020.BV_DATA[1-1].END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                WS_FLD_NO = BPB3020_AWA_3020.BV_DATA[1-1].END_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
        if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
            if (BPB3020_AWA_3020.BV_DATA[1-1].HEAD_NO.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_HEADNO;
                WS_FLD_NO = BPB3020_AWA_3020.BV_DATA[1-1].HEAD_NO_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.trim().length() > 0 
                || BPB3020_AWA_3020.BV_DATA[1-1].END_NO.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                WS_FLD_NO = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
            || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
            if (BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO == null) BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO = "";
            JIBS_tmp_int = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO += " ";
            if (BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
            else WS_COMP_BEGNO = Long.parseLong(BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.substring(0, WS_BVNO_LEN));
            if (BPB3020_AWA_3020.BV_DATA[1-1].END_NO == null) BPB3020_AWA_3020.BV_DATA[1-1].END_NO = "";
            JIBS_tmp_int = BPB3020_AWA_3020.BV_DATA[1-1].END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3020_AWA_3020.BV_DATA[1-1].END_NO += " ";
            if (BPB3020_AWA_3020.BV_DATA[1-1].END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
            else WS_COMP_ENDNO = Long.parseLong(BPB3020_AWA_3020.BV_DATA[1-1].END_NO.substring(0, WS_BVNO_LEN));
            if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                WS_FLD_NO = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE;
            BPCSNOCK.BEG_NO = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO;
            BPCSNOCK.END_NO = BPB3020_AWA_3020.BV_DATA[1-1].END_NO;
            BPCSNOCK.NUM = BPB3020_AWA_3020.BV_DATA[1-1].NUM;
            BPCSNOCK.FUNC = '1';
            S000_CALL_BPZSNOCK();
        }
    }
    public void B020_BV_DESTROY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVDE);
        BPCSBVDE.BV_CODE = BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE;
        BPCSBVDE.BV_NAME = BPB3020_AWA_3020.BV_DATA[1-1].BV_NAME;
        CEP.TRC(SCCGWA, BPB3020_AWA_3020.DE_TYP);
        if (BPB3020_AWA_3020.DE_TYP == 'Y') {
            BPCSBVDE.DE_TYP = '0';
            CEP.TRC(SCCGWA, "A");
        } else {
            BPCSBVDE.DE_TYP = '1';
            CEP.TRC(SCCGWA, "P");
        }
        BPCSBVDE.HEAD_NO = BPB3020_AWA_3020.BV_DATA[1-1].HEAD_NO;
        BPCSBVDE.BEG_NO = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO;
        BPCSBVDE.END_NO = BPB3020_AWA_3020.BV_DATA[1-1].END_NO;
        BPCSBVDE.NUM = BPB3020_AWA_3020.BV_DATA[1-1].NUM;
        CEP.TRC(SCCGWA, BPB3020_AWA_3020.BV_DATA[1-1].HEAD_NO);
        CEP.TRC(SCCGWA, BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO);
        CEP.TRC(SCCGWA, BPB3020_AWA_3020.BV_DATA[1-1].END_NO);
        CEP.TRC(SCCGWA, BPB3020_AWA_3020.BV_DATA[1-1].NUM);
        CEP.TRC(SCCGWA, BPCSBVDE.DE_TYP);
        S000_CALL_BPZSBVDE();
    }
    public void S000_CALL_BPZSNOCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_NUM_CHK, BPCSNOCK);
    }
    public void S000_CALL_BPZSBVDE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BV_DESTROY, BPCSBVDE);
        if (BPCSBVDE.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSBVDE.RC);
            WS_FLD_NO = BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
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
