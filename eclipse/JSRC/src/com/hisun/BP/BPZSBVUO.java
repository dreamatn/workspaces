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

public class BPZSBVUO {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP158";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_U_BV_OUT = "BP-U-TLR-BV-OUT     ";
    String CPN_F_TLR_BV_QUERY = "BP-F-TLR-BV-QUERY   ";
    String CPN_R_MGM_BMOV = "BP-R-MGM-BMOV       ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String CPN_R_MGM_BUSE = "BP-R-MGM-BUSE       ";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_BV_CNT = 0;
    String BPZSBVUO_FILLER1 = "BV CODE:";
    String WS_HIS_BVCODE = " ";
    String BPZSBVUO_FILLER3 = "HEAD NO:";
    String WS_HIS_HEADNO = " ";
    String BPZSBVUO_FILLER5 = "BEG NO:";
    String WS_HIS_BEGNO = " ";
    String BPZSBVUO_FILLER7 = "END NO:";
    String WS_HIS_ENDNO = " ";
    String BPZSBVUO_FILLER9 = "NUM NO:";
    int WS_HIS_NUMNO = 0;
    char WS_INVT_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRBMOV BPRBMOV = new BPRBMOV();
    BPRBUSE BPRBUSE = new BPRBUSE();
    BPRINVT BPRINVT = new BPRINVT();
    BPCO158 BPCO158 = new BPCO158();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCUBVOU BPCUBVOU = new BPCUBVOU();
    BPCFBVTL BPCFBVTL = new BPCFBVTL();
    BPCRBMOV BPCRBMOV = new BPCRBMOV();
    BPCRBUSE BPCRBUSE = new BPCRBUSE();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSBVUO BPCSBVUO;
    public void MP(SCCGWA SCCGWA, BPCSBVUO BPCSBVUO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBVUO = BPCSBVUO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBVUO return!");
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
        B020_BV_PAY_OUT();
        if (pgmRtn) return;
        B050_REC_NHIS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B060_DATA_OUTPUT();
            if (pgmRtn) return;
            BPCSBVUO.CONF_NO = SCCGWA.COMM_AREA.JRN_NO;
            BPCSBVUO.MOV_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANCEL_NO_TERTIAN;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_IND);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVUO.BV_CODE;
            S000_CALL_BPZFBVQU();
            if (pgmRtn) return;
        } else {
            B012_CHK_BR_TLR();
            if (pgmRtn) return;
        }
    }
    public void B011_CHK_BMOV_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRBMOV);
        IBS.init(SCCGWA, BPRBMOV);
        CEP.TRC(SCCGWA, BPCSBVUO.MOV_DT);
        CEP.TRC(SCCGWA, BPCSBVUO.CONF_NO);
        BPRBMOV.KEY.MOV_DT = BPCSBVUO.MOV_DT;
        BPRBMOV.KEY.CONF_NO = BPCSBVUO.CONF_NO;
        BPCRBMOV.INFO.FUNC = 'Q';
        S000_CALL_BPZRBMOV();
        if (pgmRtn) return;
        if (BPCRBMOV.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRBMOV.MOV_STS != '0') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOVSTS_MUST_0;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B012_CHK_BR_TLR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR);
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            && BPCSBVUO.PAY_TYP == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BVLT_TLR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_STSW.substring(0, 1));
        CEP.TRC(SCCGWA, BPCSBVUO.PAY_TYP);
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(0, 1).equalsIgnoreCase("1") 
            && BPCSBVUO.PAY_TYP == '0') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BBOX_TLR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCFBVTL);
        BPCFBVTL.TLR = SCCGWA.COMM_AREA.TL_ID;
        if (BPCFBVTL.BV_BBAL_FLG == 'N' 
            && BPCSBVUO.PAY_TYP == '0') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_BBOX_NOTFOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCFBVTL.BV_VBAL_FLG == 'N' 
            && BPCSBVUO.PAY_TYP == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_BVLT_NOTFOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVUO.BV_CODE;
        S000_CALL_BPZFBVQU();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRINVT);
        BPRINVT.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRINVT.TLR_D = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, "NCB0821001");
        CEP.TRC(SCCGWA, BPRINVT.KEY.DATE);
        CEP.TRC(SCCGWA, BPRINVT.TLR_D);
        T000_READ_BPTINVT_FIRST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_INVT_FLG);
        if (WS_INVT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "NCB0821002");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_BOX_CHK_INVT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTINVT_FIRST() throws IOException,SQLException,Exception {
        BPTINVT_RD = new DBParm();
        BPTINVT_RD.TableName = "BPTINVT";
        BPTINVT_RD.where = "'DATE` = :INVT_DATE AND  TLR_D = :INVT_TLR_D AND  CB_TYP = `1' "
            + "AND INVNTYP = '2' "
            + "AND VB_FLG = '0'";
        BPTINVT_RD.fst = true;
        BPTINVT_RD.order = "JRNNO";
        IBS.READ(SCCGWA, BPRINVT, this, BPTINVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "111");
            WS_INVT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "222");
            WS_INVT_FLG = 'N';
        } else {
            CEP.TRC(SCCGWA, "333");
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTINVT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B020_BV_PAY_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUBVOU);
        BPCUBVOU.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBVOU.TYPE = BPCFBVQU.TX_DATA.TYPE;
        BPCUBVOU.BV_CODE = BPCSBVUO.BV_CODE;
        BPCUBVOU.HEAD_NO = BPCSBVUO.HEAD_NO;
        BPCUBVOU.BEG_NO = BPCSBVUO.BEG_NO;
        BPCUBVOU.END_NO = BPCSBVUO.END_NO;
        BPCUBVOU.NUM = BPCSBVUO.NUM;
        BPCUBVOU.DRW_NM = BPCSBVUO.DRW_NM;
        BPCUBVOU.DRW_ID_TYP = BPCSBVUO.DRW_IDT;
        BPCUBVOU.DRW_ID_NO = BPCSBVUO.DRW_IDN;
        if (BPCSBVUO.PAY_TYP == '0') {
            BPCUBVOU.VB_FLG = '0';
        } else {
            BPCUBVOU.VB_FLG = '1';
        }
        BPCUBVOU.BV_STS = '0';
        BPCUBVOU.AC_TYP = '0';
        S000_CALL_BPZUBVOU();
        if (pgmRtn) return;
    }
    public void B021_BUSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        CEP.TRC(SCCGWA, BPCSBVUO.BV_CODE);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVUO.BV_CODE;
        S000_CALL_BPZFBVQU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSBVUO.BV_CODE);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.AC_TYP);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.TYPE);
        CEP.TRC(SCCGWA, BPCUBVOU.AC_TYP);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.USE_MODE);
        if (BPCFBVQU.TX_DATA.TYPE != '1' 
            && BPCFBVQU.TX_DATA.CTL_FLG != '0') {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B021_ADD_BUSE_PROCESS();
                if (pgmRtn) return;
            } else {
                B022_ADD_BUSE_PROCESS_CANCEL();
                if (pgmRtn) return;
            }
        }
    }
    public void B021_ADD_BUSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.KEY.BV_CODE = BPCSBVUO.BV_CODE;
        BPRBUSE.KEY.HEAD_NO = BPCSBVUO.HEAD_NO;
        BPRBUSE.KEY.BEG_NO = BPCSBVUO.BEG_NO;
        BPRBUSE.KEY.END_NO = BPCSBVUO.END_NO;
        BPRBUSE.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRBUSE.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BPCUBVOU.TYPE = BPCFBVQU.TX_DATA.TYPE;
        BPRBUSE.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRBUSE.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBUSE.TX_AUTH = " ";
        BPRBUSE.REC_STS = '0';
        BPCRBUSE.INFO.FUNC = 'A';
        S000_CALL_BPZRBUSE();
        if (pgmRtn) return;
    }
    public void B022_ADD_BUSE_PROCESS_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.KEY.BV_CODE = BPCSBVUO.BV_CODE;
        BPRBUSE.KEY.HEAD_NO = BPCSBVUO.HEAD_NO;
        BPRBUSE.KEY.BEG_NO = BPCSBVUO.BEG_NO;
        BPRBUSE.KEY.END_NO = BPCSBVUO.END_NO;
        BPRBUSE.KEY.TX_DT = BPCSBVUO.MOV_DT;
        BPRBUSE.KEY.TX_JRN = BPCSBVUO.CONF_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BPCSBVUO.BV_CODE);
        CEP.TRC(SCCGWA, BPCSBVUO.HEAD_NO);
        CEP.TRC(SCCGWA, BPCSBVUO.BEG_NO);
        CEP.TRC(SCCGWA, BPCSBVUO.END_NO);
        CEP.TRC(SCCGWA, BPCSBVUO.MOV_DT);
        CEP.TRC(SCCGWA, BPCSBVUO.CONF_NO);
        BPCRBUSE.INFO.FUNC = 'R';
        S000_CALL_BPZRBUSE();
        if (pgmRtn) return;
        if (BPCRBUSE.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPRBUSE.REC_STS = '1';
        BPCRBUSE.INFO.FUNC = 'U';
        S000_CALL_BPZRBUSE();
        if (pgmRtn) return;
    }
    public void B050_REC_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        WS_HIS_BVCODE = BPCSBVUO.BV_CODE;
        WS_HIS_HEADNO = BPCSBVUO.HEAD_NO;
        WS_HIS_BEGNO = BPCSBVUO.BEG_NO;
        WS_HIS_ENDNO = BPCSBVUO.END_NO;
        WS_HIS_NUMNO = BPCSBVUO.NUM;
        BPCPNHIS.INFO.TX_RMK = IBS.CLS2CPY(SCCGWA, BPZSBVUO_WS2);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B060_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R020_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO158;
