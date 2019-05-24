package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3806 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String CPN_S_BV_NO_INQ = "BP-S-BV-NO-INQ ";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY";
    String BP_P_INQ_ORG_REL = "BP-P-INQ-ORG-REL";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_TEXT = " ";
    int WS_I = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBVTL BPCSBVTL = new BPCSBVTL();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCPQORR BPCPQORR = new BPCPQORR();
    SCCGWA SCCGWA;
    BPB3800_AWA_3800 BPB3800_AWA_3800;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3806 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3800_AWA_3800>");
        BPB3800_AWA_3800 = (BPB3800_AWA_3800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BROWSE_TBVD_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPB3800_AWA_3800.BV_CODE;
        CEP.TRC(SCCGWA, BPB3800_AWA_3800.BV_CODE);
        S000_CALL_BPZFBVQU();
        if (BPB3800_AWA_3800.HEAD_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "CHK HEAD NO!");
            CEP.TRC(SCCGWA, BPB3800_AWA_3800.HEAD_NO);
            if (BPB3800_AWA_3800.HEAD_NO == null) BPB3800_AWA_3800.HEAD_NO = "";
            JIBS_tmp_int = BPB3800_AWA_3800.HEAD_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) BPB3800_AWA_3800.HEAD_NO += " ";
            for (WS_I = 1; WS_I <= 10 
                && BPB3800_AWA_3800.HEAD_NO.substring(WS_I - 1, WS_I + 1 - 1).trim().length() != 0; WS_I += 1) {
            }
            CEP.TRC(SCCGWA, BPCFBVQU);
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.HEAD_LENGTH);
            if (BPCFBVQU.TX_DATA.HEAD_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_HEADNO_LEN;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.HEAD_LENGTH);
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPB3800_AWA_3800.HEAD_NO);
        }
        CEP.TRC(SCCGWA, BPB3800_AWA_3800.BR);
        if (BPB3800_AWA_3800.BR == 0) {
        } else {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB3800_AWA_3800.BR;
            S000_CALL_BPZPQORG();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
                WS_FLD_NO = BPB3800_AWA_3800.BR_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB3800_AWA_3800.BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
        } else {
            IBS.init(SCCGWA, BPCPRGST);
            BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCPRGST.BR2 = BPB3800_AWA_3800.BR;
            S000_CALL_BPZPRGST();
            if (BPCPRGST.LVL_RELATION == 'C') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_LOW_BR;
                WS_FLD_NO = BPB3800_AWA_3800.BR_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPB3800_AWA_3800.TLR);
        if (BPB3800_AWA_3800.TLR.trim().length() == 0) {
        } else {
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = BPB3800_AWA_3800.TLR;
            S000_CALL_BPZFTLRQ();
            if (BPCFTLRQ.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOTFND;
                WS_FLD_NO = BPB3800_AWA_3800.TLR_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_BROWSE_TBVD_RECORD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3800_AWA_3800.BV_CODE);
        IBS.init(SCCGWA, BPCSBVTL);
        if (BPB3800_AWA_3800.BR == 0) {
            BPCSBVTL.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        } else {
            BPCSBVTL.BR = BPB3800_AWA_3800.BR;
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPCSBVTL.BR;
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, "NCB032801");
        CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
        CEP.TRC(SCCGWA, BPCPQORG.INT_BR_FLG);
        if (!BPCPQORG.TRA_TYP.equalsIgnoreCase("00") 
            && BPCPQORG.INT_BR_FLG == 'Y') {
            IBS.init(SCCGWA, BPCPQORR);
            BPCPQORR.TYP = "12";
            BPCPQORR.BR = BPCSBVTL.BR;
            S000_CALL_BPZPQORR();
            BPCSBVTL.BR = BPCPQORR.REL_BR;
        } else {
        }
        CEP.TRC(SCCGWA, "NCB0531001");
        CEP.TRC(SCCGWA, BPCSBVTL.BR);
        CEP.TRC(SCCGWA, BPB3800_AWA_3800.TLR);
        if (BPB3800_AWA_3800.TLR.trim().length() == 0) {
            BPCSBVTL.TLR = SCCGWA.COMM_AREA.TL_ID;
        } else {
            BPCSBVTL.TLR = BPB3800_AWA_3800.TLR;
        }
        CEP.TRC(SCCGWA, BPB3800_AWA_3800.BV_NUM);
        if (BPB3800_AWA_3800.BV_NUM == 0) {
            BPCSBVTL.NUM = 999999999;
        } else {
            BPCSBVTL.NUM = BPB3800_AWA_3800.BV_NUM;
        }
        CEP.TRC(SCCGWA, BPCSBVTL.NUM);
        BPCSBVTL.BV_CODE = BPB3800_AWA_3800.BV_CODE;
        BPCSBVTL.HEAD_NO = BPB3800_AWA_3800.HEAD_NO;
        S000_CALL_BPZSBVTL();
    }
    public void S000_CALL_BPZPQORR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, BP_P_INQ_ORG_REL, BPCPQORR);
        if (BPCPQORR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_P_INQ_ORG_STATION;
        SCCCALL.COMMAREA_PTR = BPCPRGST;
        SCCCALL.ERR_FLDNO = BPB3800_AWA_3800.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            WS_FLD_NO = BPB3800_AWA_3800.BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
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
    public void S000_CALL_BPZSBVTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BV_NO_INQ, BPCSBVTL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
