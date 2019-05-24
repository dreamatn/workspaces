package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2911 {
    int JIBS_tmp_int;
    DBParm BPTMTPA_RD;
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTVHPB_RD;
    DBParm BPTTBVD_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP251";
    String CPN_S_BVAPP_MAINTAIN = "BP-S-BVAPP-MAINTAIN";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String WS_ERR_MSG = " ";
    String WS_ERR_INF = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_CNT = 0;
    int WS_BVNO_LEN = 0;
    long WS_COMP_BEGNO = 0;
    long WS_COMP_ENDNO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRMTPA BPRMTPA = new BPRMTPA();
    SCCFMT SCCFMT = new SCCFMT();
    BPCOAPLL BPCOAPLL = new BPCOAPLL();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCOPLIR BPCOPLIR = new BPCOPLIR();
    BPRTBVD BPRTBVD = new BPRTBVD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB2911_AWA_2911 BPB2911_AWA_2911;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT2911 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2911_AWA_2911>");
        BPB2911_AWA_2911 = (BPB2911_AWA_2911) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BV_APP_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2911_AWA_2911.APP_TYPE);
        if (BPB2911_AWA_2911.APP_TYPE == '0') {
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
            S000_CALL_BPZFTLRQ();
            if (pgmRtn) return;
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_STSW.substring(0, 1));
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_STSW.substring(2 - 1, 2 + 1 - 1));
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_VLT_TLR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPB2911_AWA_2911.BV_INFO[1-1].BV_CODE);
        if (BPB2911_AWA_2911.BV_INFO[1-1].BV_CODE.equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_MUST_INPUT;
            WS_FLD_NO = BPB2911_AWA_2911.BV_INFO[1-1].BV_CODE_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB2911_AWA_2911.BV_INFO[1-1].BV_CODE);
        for (WS_J = 1; WS_J <= 10 
            && !BPB2911_AWA_2911.BV_INFO[WS_J-1].BV_CODE.equalsIgnoreCase("0") 
            && BPB2911_AWA_2911.BV_INFO[WS_J-1].BV_CODE.trim().length() != 0; WS_J += 1) {
            CEP.TRC(SCCGWA, WS_J);
            CEP.TRC(SCCGWA, BPB2911_AWA_2911.BV_INFO[WS_J-1].APP_NUM);
            CEP.TRC(SCCGWA, BPB2911_AWA_2911.BV_INFO[WS_J-1].BV_CODE);
            if (BPB2911_AWA_2911.BV_INFO[WS_J-1].APP_NUM <= 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NUM_NOT_LESS_ZERO;
                WS_FLD_NO = (short) BPB2911_AWA_2911.BV_INFO[WS_J-1].APP_NUM;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPB2911_AWA_2911.BV_INFO[WS_J-1].BV_CODE;
            S000_CALL_BPZFBVQU();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_J);
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB2911_AWA_2911.BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
        }
        if (BPB2911_AWA_2911.APP_TYPE == '0') {
            IBS.init(SCCGWA, BPRVHPB);
            IBS.init(SCCGWA, BPCRVHPB);
            BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPRVHPB.POLL_BOX_IND = '1';
            BPRVHPB.RELATE_FLG = 'Y';
            BPRVHPB.STS = 'N';
            BPCRVHPB.INFO.FUNC = 'F';
            S000_CALL_BPZRVHPB();
            if (pgmRtn) return;
            if (BPCRVHPB.RETURN_INFO == 'F') {
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_HVNT_BVP;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, BPRMTPA);
        BPRMTPA.KEY.MT_BR = BPB2911_AWA_2911.BR;
        BPRMTPA.KEY.APP_FLG = '2';
        BPRMTPA.MT_FLG = '0';
        TOOO_READ_BPTMTPA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRMTPA.STAT_TM);
        CEP.TRC(SCCGWA, BPRMTPA.END_TM);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (SCCGWA.COMM_AREA.TR_TIME >= BPRMTPA.STAT_TM 
                && SCCGWA.COMM_AREA.TR_TIME <= BPRMTPA.END_TM) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_APP_TM_NOT_MTPA);
            }
        }
    }
    public void S000_CALL_BPZRVHPB() throws IOException,SQLException,Exception {
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        BPCRVHPB.INFO.LEN = 152;
        IBS.CALLCPN(SCCGWA, CPN_R_BPTVHPB_MTN, BPCRVHPB);
        if (BPCRVHPB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRVHPB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void TOOO_READ_BPTMTPA() throws IOException,SQLException,Exception {
        BPTMTPA_RD = new DBParm();
        BPTMTPA_RD.TableName = "BPTMTPA";
        BPTMTPA_RD.where = "MT_BR = :BPRMTPA.KEY.MT_BR "
            + "AND APP_FLG = :BPRMTPA.KEY.APP_FLG "
            + "AND MT_FLG = :BPRMTPA.MT_FLG";
        BPTMTPA_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRMTPA, this, BPTMTPA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void B020_BV_APP_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOPLIR);
        for (WS_CNT = 1; WS_CNT <= 10 
            && !BPB2911_AWA_2911.BV_INFO[WS_CNT-1].BV_CODE.equalsIgnoreCase("0") 
            && BPB2911_AWA_2911.BV_INFO[WS_CNT-1].BV_CODE.trim().length() != 0; WS_CNT += 1) {
            IBS.init(SCCGWA, BPCOAPLL);
            CEP.TRC(SCCGWA, BPCOAPLL.BR);
            CEP.TRC(SCCGWA, BPCOAPLL.APP_NOTE);
            CEP.TRC(SCCGWA, BPCOAPLL.APP_DT);
            BPCOAPLL.FUNC = 'A';
            BPCOAPLL.OUTPUT_FMT = K_OUTPUT_FMT;
            BPCOAPLL.BR = BPB2911_AWA_2911.BR;
            BPCOAPLL.APP_NOTE = BPB2911_AWA_2911.APP_NOTE;
            BPCOAPLL.APP_DT = BPB2911_AWA_2911.APP_DT;
            CEP.TRC(SCCGWA, BPCOAPLL.BR);
            CEP.TRC(SCCGWA, BPCOAPLL.APP_NOTE);
            CEP.TRC(SCCGWA, BPCOAPLL.APP_DT);
            CEP.TRC(SCCGWA, BPB2911_AWA_2911);
            CEP.TRC(SCCGWA, WS_CNT);
            BPCOAPLL.BV_INFO[1-1].BV_CODE = BPB2911_AWA_2911.BV_INFO[WS_CNT-1].BV_CODE;
            CEP.TRC(SCCGWA, BPCOAPLL.BV_INFO[1-1].BV_CODE);
            BPCOAPLL.BV_INFO[1-1].APP_NUM = BPB2911_AWA_2911.BV_INFO[WS_CNT-1].APP_NUM;
            CEP.TRC(SCCGWA, BPB2911_AWA_2911.BV_INFO[WS_CNT-1].BEG_NO);
            CEP.TRC(SCCGWA, BPB2911_AWA_2911.BV_INFO[WS_CNT-1].END_NO);
            BPCOAPLL.BV_INFO[1-1].BEG_NO1 = BPB2911_AWA_2911.BV_INFO[WS_CNT-1].BEG_NO;
            BPCOAPLL.BV_INFO[1-1].END_NO1 = BPB2911_AWA_2911.BV_INFO[WS_CNT-1].END_NO;
            BPCOAPLL.BV_INFO[1-1].NUM1 = BPB2911_AWA_2911.BV_INFO[WS_CNT-1].APP_NUM;
            CEP.TRC(SCCGWA, BPB2911_AWA_2911.APP_TYPE);
            BPCOAPLL.APP_TYPE = BPB2911_AWA_2911.APP_TYPE;
            if (BPB2911_AWA_2911.APP_TYPE == '0') {
                BPCOAPLL.BV_INFO[1-1].STS = '0';
            } else {
                IBS.init(SCCGWA, BPRVHPB);
                BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPRVHPB.POLL_BOX_IND = '1';
                T000_READ_BPTVHPB();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
                IBS.init(SCCGWA, BPRTBVD);
                BPRTBVD.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPRTBVD.KEY.PL_BOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
                BPRTBVD.KEY.BV_CODE = BPCOAPLL.BV_INFO[WS_CNT-1].BV_CODE;
                BPRTBVD.KEY.VALUE = 0;
                BPRTBVD.KEY.HEAD_NO = " ";
                BPRTBVD.BEG_NO = BPCOAPLL.BV_INFO[WS_CNT-1].BEG_NO1;
                BPRTBVD.KEY.END_NO = BPCOAPLL.BV_INFO[WS_CNT-1].END_NO1;
                CEP.TRC(SCCGWA, BPRTBVD.KEY.BR);
                CEP.TRC(SCCGWA, BPRTBVD.KEY.PL_BOX_NO);
                CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
                CEP.TRC(SCCGWA, BPRTBVD.KEY.VALUE);
                CEP.TRC(SCCGWA, BPRTBVD.KEY.HEAD_NO);
                CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
                CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
                T000_READ_BPTTBVD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPRTBVD.KEY.STS);
                if (BPRTBVD.KEY.STS != '0' 
                    && BPRTBVD.KEY.STS != '1') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR178);
                }
                BPCOAPLL.BV_INFO[1-1].STS = BPRTBVD.KEY.STS;
            }
            CEP.TRC(SCCGWA, WS_CNT);
            S000_CALL_BPZSAPLL();
            if (pgmRtn) return;
            BPCOPLIR.BR = BPCOAPLL.BR;
            BPCOPLIR.APP_STS = '0';
            BPCOPLIR.APP_TLR = SCCGWA.COMM_AREA.TL_ID;
            CEP.TRC(SCCGWA, "STS0001");
            CEP.TRC(SCCGWA, BPCOAPLL.APP_NO);
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, BPCOAPLL.BV_INFO[1-1].BV_CODE);
            CEP.TRC(SCCGWA, BPCOAPLL.BV_INFO[1-1].APP_NUM);
            BPCOPLIR.BV_INFO[WS_CNT-1].APP_NO = BPCOAPLL.APP_NO;
            BPCOPLIR.BV_INFO[WS_CNT-1].BV_CODE = BPCOAPLL.BV_INFO[WS_CNT-1].BV_CODE;
            BPCOPLIR.BV_INFO[WS_CNT-1].APP_NUM = BPCOAPLL.BV_INFO[WS_CNT-1].APP_NUM;
            BPCOPLIR.APP_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCOPLIR.APP_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCOPLIR.APP_NOTE = BPCOAPLL.APP_NOTE;
        }
        R010_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTVHPB() throws IOException,SQLException,Exception {
        BPTVHPB_RD = new DBParm();
        BPTVHPB_RD.TableName = "BPTVHPB";
        BPTVHPB_RD.where = "BR = :BPRVHPB.BR "
            + "AND POLL_BOX_IND = :BPRVHPB.POLL_BOX_IND";
        IBS.READ(SCCGWA, BPRVHPB, this, BPTVHPB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR180);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTVHPB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTTBVD() throws IOException,SQLException,Exception {
        BPTTBVD_RD = new DBParm();
        BPTTBVD_RD.TableName = "BPTTBVD";
        BPTTBVD_RD.where = "BR = :BPRTBVD.KEY.BR "
            + "AND PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND BV_CODE = :BPRTBVD.KEY.BV_CODE "
            + "AND VALUE = :BPRTBVD.KEY.VALUE "
            + "AND HEAD_NO = :BPRTBVD.KEY.HEAD_NO "
            + "AND END_NO >= :BPRTBVD.BEG_NO "
            + "AND BEG_NO <= :BPRTBVD.KEY.END_NO "
            + "AND BEG_NO <= END_NO";
        IBS.READ(SCCGWA, BPRTBVD, this, BPTTBVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR181);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTBVD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R010_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCOAPLL.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOPLIR;
        SCCFMT.DATA_LEN = 379;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSAPLL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BVAPP_MAINTAIN, BPCOAPLL);
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INF, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_ERR_INF, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
