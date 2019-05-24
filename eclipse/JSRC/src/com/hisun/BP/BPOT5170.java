package com.hisun.BP;

import com.hisun.FX.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5170 {
    int JIBS_tmp_int;
    DBParm BPTEXRP_RD;
    String CPN_S_INQ_TXN_QTP = "BP-INQ-TXN-QTP   ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_INQ_EXR_CODE = "BP-INQ-EXR-CODE   ";
    int WS_I = 0;
    String WS_CCY = " ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCQQTP BPCQQTP = new BPCQQTP();
    BPCOIEC BPCOIEC = new BPCOIEC();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPREXRP BPREXRP = new BPREXRP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPB5170_AWA_5170 BPB5170_AWA_5170;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5170 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5170_AWA_5170>");
        BPB5170_AWA_5170 = (BPB5170_AWA_5170) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQ_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5170_AWA_5170.BR);
        CEP.TRC(SCCGWA, BPB5170_AWA_5170.EXR_TYPE);
        CEP.TRC(SCCGWA, BPB5170_AWA_5170.CCY);
        CEP.TRC(SCCGWA, BPB5170_AWA_5170.CCY2);
        CEP.TRC(SCCGWA, BPB5170_AWA_5170.STR_DATE);
        CEP.TRC(SCCGWA, BPB5170_AWA_5170.STR_TIME);
        CEP.TRC(SCCGWA, BPB5170_AWA_5170.END_DATE);
        CEP.TRC(SCCGWA, BPB5170_AWA_5170.END_TIME);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
        IBS.init(SCCGWA, BPREXRT);
        IBS.init(SCCGWA, BPCPRMR);
        BPREXRT.KEY.TYP = "EXRT";
        BPREXRT.KEY.CD = BPB5170_AWA_5170.EXR_TYPE;
        BPCPRMR.DAT_PTR = BPREXRT;
        S000_CALL_BPZPRMR();
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INVALID_EXRGROP);
        }
        IBS.init(SCCGWA, BPCOIEC);
        BPCOIEC.CCY1 = BPB5170_AWA_5170.CCY;
        BPCOIEC.CCY2 = BPB5170_AWA_5170.CCY2;
        S000_CALL_BPZSIEC();
        CEP.TRC(SCCGWA, BPCOIEC.EXR_CODE);
        if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
        JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
        if (!BPB5170_AWA_5170.CCY.equalsIgnoreCase(BPCOIEC.EXR_CODE.substring(0, 3))) {
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            BPB5170_AWA_5170.CCY = BPCOIEC.EXR_CODE.substring(0, 3);
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            BPB5170_AWA_5170.CCY2 = BPCOIEC.EXR_CODE.substring(4 - 1, 4 + 3 - 1);
        }
        CEP.TRC(SCCGWA, BPB5170_AWA_5170.BR);
        B030_COMPARE_BR();
        if (BPCPRGST.LVL_RELATION == 'A') {
        } else {
            if (BPB5170_AWA_5170.BR == 999999) {
            } else {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_BR_LVL_ERR);
            }
        }
        CEP.TRC(SCCGWA, BPB5170_AWA_5170.CCY);
        IBS.init(SCCGWA, BPREXRP);
        BPREXRP.KEY.BR = BPB5170_AWA_5170.BR;
        BPREXRP.KEY.EXR_TYP = BPB5170_AWA_5170.EXR_TYPE;
        BPREXRP.KEY.CCY = BPB5170_AWA_5170.CCY;
        BPTEXRP_RD = new DBParm();
        BPTEXRP_RD.TableName = "BPTEXRP";
        IBS.READ(SCCGWA, BPREXRP, BPTEXRP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPB5170_AWA_5170.BR = 999999;
        } else {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_EXRP_EXCP);
        }
        CEP.TRC(SCCGWA, BPB5170_AWA_5170.BR);
    }
    public void B030_COMPARE_BR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BPB5170_AWA_5170.BR);
        if (BPB5170_AWA_5170.BR != 999999) {
            IBS.init(SCCGWA, BPCPRGST);
            BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCPRGST.BR2 = BPB5170_AWA_5170.BR;
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == BPB5170_AWA_5170.BR) {
                BPCPRGST.LVL_RELATION = 'A';
            } else {
                S000_CALL_BPZPRGST();
            }
            CEP.TRC(SCCGWA, BPCPRGST.FLAG);
            CEP.TRC(SCCGWA, BPCPRGST.LVL_RELATION);
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-STATION", BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPRGST.RC);
        }
    }
    public void B020_INQ_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQQTP);
        BPCQQTP.BR = BPB5170_AWA_5170.BR;
        BPCQQTP.EXR_TYP = BPB5170_AWA_5170.EXR_TYPE;
        BPCQQTP.CCY = BPB5170_AWA_5170.CCY;
        BPCQQTP.CORR_CCY = BPB5170_AWA_5170.CCY2;
        BPCQQTP.STR_DATE = BPB5170_AWA_5170.STR_DATE;
        BPCQQTP.STR_TIME = BPB5170_AWA_5170.STR_TIME;
        BPCQQTP.END_DATE = BPB5170_AWA_5170.END_DATE;
        BPCQQTP.END_TIME = BPB5170_AWA_5170.END_TIME;
        CEP.TRC(SCCGWA, BPCQQTP.STR_DATE);
        CEP.TRC(SCCGWA, BPCQQTP.STR_TIME);
        CEP.TRC(SCCGWA, BPCQQTP.END_DATE);
        CEP.TRC(SCCGWA, BPCQQTP.END_TIME);
        S00_CALL_BPZQQTP();
    }
    public void S00_CALL_BPZQQTP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_INQ_TXN_QTP, BPCQQTP);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
    }
    public void S000_CALL_BPZSIEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_EXR_CODE, BPCOIEC);
        if (BPCOIEC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOIEC.RC);
            S000_ERR_MSG_PROC();
        }
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
