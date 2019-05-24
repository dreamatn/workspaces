package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBVRV {
    char STS_NORMAL = '0';
    String REC_NHIS = "BP-REC-NHIS         ";
    String U_TLR_BV_RVK = "BP-U-TLR-BV-RVK     ";
    String R_MGM_TBVD = "BP-R-MGM-TBVD";
    String R_MGM_BUSE = "BP-R-MGM-BUSE       ";
    String F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    BPZSBVRV_WS_VARIABLES WS_VARIABLES = new BPZSBVRV_WS_VARIABLES();
    BPZSBVRV_WS_HIS_REMARKS WS_HIS_REMARKS = new BPZSBVRV_WS_HIS_REMARKS();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRBUSE BPRBUSE = new BPRBUSE();
    BPCO159 BPCO159 = new BPCO159();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCUBVRV BPCUBVRV = new BPCUBVRV();
    BPCRTBVD BPCRTBVD = new BPCRTBVD();
    BPCRBUSE BPCRBUSE = new BPCRBUSE();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPRTBVD BPRTBVD = new BPRTBVD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    BPCSBVRV BPCSBVRV;
    public void MP(SCCGWA SCCGWA, BPCSBVRV BPCSBVRV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBVRV = BPCSBVRV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSBVRV return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_HIS_REMARKS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_CALL_BPZUBVRV();
        B040_REC_NHIS();
        B090_DATA_OUTPUT();
    }
    public void B020_CALL_BPZUBVRV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVRV.TX_DATA.BV_CODE;
        S000_CALL_BPZFBVQU();
        IBS.init(SCCGWA, BPCUBVRV);
        BPCUBVRV.FUNC = '4';
        BPCUBVRV.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBVRV.TYPE = BPCFBVQU.TX_DATA.TYPE;
        BPCUBVRV.BV_CODE = BPCSBVRV.TX_DATA.BV_CODE;
        BPCUBVRV.HEAD_NO = BPCSBVRV.TX_DATA.HEAD_NO;
        BPCUBVRV.BEG_NO = BPCSBVRV.TX_DATA.BEG_NO;
        BPCUBVRV.END_NO = BPCSBVRV.TX_DATA.END_NO;
        BPCUBVRV.NUM = BPCSBVRV.TX_DATA.NUM;
        BPCUBVRV.VB_FLG = BPCSBVRV.TX_DATA.OUT_TYP;
        S000_CALL_BPZUBVRV();
        B021_BUSE_PROCESS();
    }
    public void B021_BUSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVRV.TX_DATA.BV_CODE;
        S000_CALL_BPZFBVQU();
        if ((BPCFBVQU.TX_DATA.TYPE != '1') 
            && (BPCFBVQU.TX_DATA.CTL_FLG != '0')) {
            B021_ADD_BUSE_PROCESS();
        }
    }
    public void B021_ADD_BUSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.KEY.BV_CODE = BPCSBVRV.TX_DATA.BV_CODE;
        BPRBUSE.KEY.HEAD_NO = BPCSBVRV.TX_DATA.HEAD_NO;
        BPRBUSE.KEY.BEG_NO = BPCSBVRV.TX_DATA.BEG_NO;
        BPRBUSE.KEY.END_NO = BPCSBVRV.TX_DATA.END_NO;
        BPRBUSE.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRBUSE.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BPRBUSE.TYPE = BPCFBVQU.TX_DATA.TYPE;
        BPRBUSE.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRBUSE.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBUSE.TX_AUTH = " ";
        BPRBUSE.STS = '4';
        BPRBUSE.REC_STS = '0';
        BPCRBUSE.INFO.FUNC = 'A';
        S000_CALL_BPZRBUSE();
    }
    public void B040_REC_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        WS_HIS_REMARKS.HIS_OUT_TYP = BPCSBVRV.TX_DATA.OUT_TYP;
        WS_HIS_REMARKS.HIS_BV_CODE = BPCSBVRV.TX_DATA.BV_CODE;
        WS_HIS_REMARKS.HIS_HEAD_NO = BPCSBVRV.TX_DATA.HEAD_NO;
        WS_HIS_REMARKS.HIS_BEG_NO = BPCSBVRV.TX_DATA.BEG_NO;
        WS_HIS_REMARKS.HIS_END_NO = BPCSBVRV.TX_DATA.END_NO;
        WS_HIS_REMARKS.HIS_NUM = BPCSBVRV.TX_DATA.NUM;
        BPCPNHIS.INFO.TX_RMK = IBS.CLS2CPY(SCCGWA, WS_HIS_REMARKS);
        S000_CALL_BPZPNHIS();
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO159);
        BPCO159.OUT_TYP = BPCSBVRV.TX_DATA.OUT_TYP;
        BPCO159.BV_CODE = BPCSBVRV.TX_DATA.BV_CODE;
        BPCO159.NAME = BPCSBVRV.TX_DATA.NAME;
        BPCO159.HEAD_NO = BPCSBVRV.TX_DATA.HEAD_NO;
        BPCO159.BEG_NO = BPCSBVRV.TX_DATA.BEG_NO;
        BPCO159.END_NO = BPCSBVRV.TX_DATA.END_NO;
        BPCO159.NUM = BPCSBVRV.TX_DATA.NUM;
        BPCO159.REMARK = BPCSBVRV.TX_DATA.REMARK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSBVRV.TX_DATA.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO159;
        SCCFMT.DATA_LEN = 348;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZRTBVD() throws IOException,SQLException,Exception {
        BPCRTBVD.INFO.POINTER = BPRTBVD;
        BPCRTBVD.INFO.LEN = 6530;
        IBS.CALLCPN(SCCGWA, R_MGM_TBVD, BPCRTBVD);
        if (BPCRTBVD.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTBVD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUBVRV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, U_TLR_BV_RVK, BPCUBVRV);
    }
    public void S000_CALL_BPZRBUSE() throws IOException,SQLException,Exception {
        BPCRBUSE.INFO.POINTER = BPRBUSE;
        BPCRBUSE.INFO.LEN = 189;
        IBS.CALLCPN(SCCGWA, R_MGM_BUSE, BPCRBUSE);
        if (BPCRBUSE.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBUSE.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
