package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZSBVBB {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP155";
    String CPN_R_MGM_BUSE = "BP-R-MGM-BUSE       ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_F_K_PSW = "BP-F-K-PSW-MAINTAIN ";
    String CPN_F_C_PSW = "BP-F-C-PSW-MAINTAIN ";
    String CPN_U_BV_OUT = "BP-U-TLR-BV-OUT     ";
    String CPN_U_BV_IN = "BP-U-TLR-BV-IN      ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String WS_ERR_MSG = " ";
    String WS_TEMP_PLBOX_NO = " ";
    String WS_TLR = " ";
    String BPZSBVBB_FILLER1 = "BV CODE:";
    String WS_HIS_BVCODE = " ";
    String BPZSBVBB_FILLER3 = "HEAD NO:";
    String WS_HIS_HEADNO = " ";
    String BPZSBVBB_FILLER5 = "BEG NO:";
    String WS_HIS_BEGNO = " ";
    String BPZSBVBB_FILLER7 = "END NO:";
    String WS_HIS_ENDNO = " ";
    String BPZSBVBB_FILLER9 = "NUM NO:";
    int WS_HIS_NUMNO = 0;
    char WS_TBL_FLAG = ' ';
    int WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRBMOV BPRBMOV = new BPRBMOV();
    BPRBUSE BPRBUSE = new BPRBUSE();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCRBUSE BPCRBUSE = new BPCRBUSE();
    BPCO163 BPCO163 = new BPCO163();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFKPSW BPCFKPSW = new BPCFKPSW();
    BPCFCPSW BPCFCPSW = new BPCFCPSW();
    BPCUBVOU BPCUBVOU = new BPCUBVOU();
    BPCUBVIN BPCUBVIN = new BPCUBVIN();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRLHIS BPRLHIS = new BPRLHIS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRTBVD BPRTBVD = new BPRTBVD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSBVBB BPCSBVBB;
    public void MP(SCCGWA SCCGWA, BPCSBVBB BPCSBVBB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBVBB = BPCSBVBB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBVBB return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT();
        if (pgmRtn) return;
        B020_TLR_BVBOX_CONVERT();
        if (pgmRtn) return;
        B030_WRITE_LHIS();
        if (pgmRtn) return;
        B050_REC_NHIS();
        if (pgmRtn) return;
        B060_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSBVBB.REP_TLR.trim().length() > 0) {
            if (BPCSBVBB.REP_TLR.equalsIgnoreCase(BPCSBVBB.RCV_TLR)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANNOT_SELF_TO_SELF;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_TLR = BPCSBVBB.REP_TLR;
        } else {
            WS_TLR = SCCGWA.COMM_AREA.TL_ID;
        }
        if (WS_TLR.equalsIgnoreCase(BPCSBVBB.RCV_TLR)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANNOT_SAME_TLR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPCSBVBB.RCV_TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_BR);
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BVLT_TLR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCFTLRQ.INFO.NEW_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOTMATCH_ORG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.SIGN_STS);
        if (BPCFTLRQ.INFO.SIGN_STS != 'O' 
            && BPCFTLRQ.INFO.SIGN_STS != 'T') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_MUST_SIGN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_TLR_BVBOX_CONVERT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRVHPB.CUR_TLR = WS_TLR;
        BPRVHPB.POLL_BOX_IND = '1';
        BPRVHPB.RELATE_FLG = 'Y';
        BPRVHPB.STS = 'N';
        BPCRVHPB.INFO.FUNC = 'F';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
        CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
        if (BPCRVHPB.RETURN_INFO == 'F') {
            if (BPRVHPB.BV_CHK_FLG == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CHECK_BVP;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_TEMP_PLBOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
            }
            CEP.TRC(SCCGWA, WS_TEMP_PLBOX_NO);
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BVLIB_TLR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.KEY.POOL_BOX_NO = WS_TEMP_PLBOX_NO;
        BPCRVHPB.INFO.FUNC = 'R';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
        WS_TEMP_PLBOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
        BPRVHPB.CUR_TLR = BPCSBVBB.RCV_TLR;
        BPRVHPB.LST_TLR = WS_TLR;
        BPRVHPB.BV_CHK_FLG = 'N';
        BPRVHPB.BL_CHK_FLG = 'N';
        BPRVHPB.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRVHPB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCRVHPB.INFO.FUNC = 'U';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
    }
    public void B020_TLR_BV_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUBVOU);
        BPCUBVOU.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBVOU.TYPE = '0';
        BPCUBVOU.BV_CODE = BPCSBVBB.BV_CODE;
        BPCUBVOU.HEAD_NO = BPCSBVBB.HEAD_NO;
        BPCUBVOU.BEG_NO = BPCSBVBB.BEG_NO;
        BPCUBVOU.END_NO = BPCSBVBB.END_NO;
        BPCUBVOU.NUM = BPCSBVBB.NUM;
        BPCUBVOU.BV_STS = BPCSBVBB.BV_STS;
        BPCUBVOU.VB_FLG = '1';
        BPCUBVOU.AC_TYP = '1';
        S000_CALL_BPZUBVOU();
        if (pgmRtn) return;
    }
    public void B021_BUSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVBB.BV_CODE;
        S000_CALL_BPZFBVQU();
        if (pgmRtn) return;
        if (BPCFBVQU.TX_DATA.AC_TYP == '0' 
            && BPCFBVQU.TX_DATA.TYPE == '0' 
            && BPCUBVIN.AC_TYP == '0' 
            && BPCFBVQU.TX_DATA.USE_MODE != '0') {
            B021_ADD_BUSE_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B021_ADD_BUSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.KEY.BV_CODE = BPCSBVBB.BV_CODE;
        BPRBUSE.KEY.HEAD_NO = BPCSBVBB.HEAD_NO;
        BPRBUSE.KEY.BEG_NO = BPCSBVBB.BEG_NO;
        BPRBUSE.KEY.END_NO = BPCSBVBB.END_NO;
        BPRBUSE.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRBUSE.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BPRBUSE.TYPE = '0';
        BPRBUSE.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRBUSE.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBUSE.TX_AUTH = " ";
        BPRBUSE.STS = '0';
        BPRBUSE.REC_STS = '0';
        BPCRBUSE.INFO.FUNC = 'A';
        S000_CALL_BPZRBUSE();
        if (pgmRtn) return;
    }
    public void B030_TLR_BV_IN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUBVIN);
        BPCUBVIN.TYPE = '0';
        BPCUBVIN.TLR = BPCSBVBB.RCV_TLR;
        BPCUBVIN.BV_CODE = BPCSBVBB.BV_CODE;
        BPCUBVIN.HEAD_NO = BPCSBVBB.HEAD_NO;
        BPCUBVIN.BEG_NO = BPCSBVBB.BEG_NO;
        BPCUBVIN.END_NO = BPCSBVBB.END_NO;
        BPCUBVIN.NUM = BPCSBVBB.NUM;
        BPCUBVOU.BV_STS = BPCSBVBB.BV_STS;
        BPCUBVIN.VB_FLG = '1';
        BPCUBVIN.AC_TYP = '1';
        S000_CALL_BPZUBVIN();
        if (pgmRtn) return;
    }
    public void B030_WRITE_LHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRLHIS);
        BPRLHIS.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRLHIS.KEY.TR_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRLHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPRLHIS.BR_NM = BPCPQORG.CHN_NM;
        BPRLHIS.FROM_TLR = WS_TLR;
        BPRLHIS.TO_TLR = BPCSBVBB.RCV_TLR;
        BPRLHIS.BOX_TYPE = '2';
        BPRLHIS.BOX_NO = WS_TEMP_PLBOX_NO;
        BPRLHIS.CREATE_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRLHIS.CREATE_TLR = WS_TLR;
        BPRLHIS.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRLHIS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRLHIS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_BPTLHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.CHN_NM);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_WRITE_BPTLHIS() throws IOException,SQLException,Exception {
        BPTLHIS_RD = new DBParm();
        BPTLHIS_RD.TableName = "BPTLHIS";
        BPTLHIS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRLHIS, BPTLHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BV_FLG_HIS_SAME);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTLHIS  ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B050_REC_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        WS_HIS_BVCODE = BPCSBVBB.BV_CODE;
        WS_HIS_HEADNO = BPCSBVBB.HEAD_NO;
        WS_HIS_BEGNO = BPCSBVBB.BEG_NO;
        WS_HIS_ENDNO = BPCSBVBB.END_NO;
        WS_HIS_NUMNO = BPCSBVBB.NUM;
        BPCPNHIS.INFO.TX_RMK = IBS.CLS2CPY(SCCGWA, BPZSBVBB_WS2);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B060_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R010_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO163;
