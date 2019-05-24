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

public class BPZFUSVR {
    boolean pgmRtn = false;
    String CPN_F_MAINTAIN_PARM = "BP-F-F-MAINTAIN-PARM";
    String CPN_F_T_FEE_INFO = "BP-F-T-FEE-INFO     ";
    String CPN_REC_NHIS = "BP-REC-NHIS  ";
    String K_HIS_REMARKS = "SVR DETAILS INFO ";
    String K_HIS_COPYBOOK_NAME = "BPCOHSVR";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    short WS_CNT1 = 0;
    short WS_FEE_NO = 0;
    short WS_IDX = 0;
    short WS_NO = 0;
    char WS_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPVFSVR BPVFSVR = new BPVFSVR();
    BPVFSVR BPVHSVR = new BPVFSVR();
    BPCOHSVR BPCOHSVR = new BPCOHSVR();
    BPCOHSVR BPCNHSVR = new BPCOHSVR();
    BPVFMSK BPVFMSK = new BPVFMSK();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    SCCGWA SCCGWA;
    BPCOFSVR BPCOUSVR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOFSVR BPCOUSVR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOUSVR = BPCOUSVR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFUSVR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPVFSVR);
        IBS.init(SCCGWA, BPVHSVR);
        IBS.init(SCCGWA, BPCOHSVR);
        IBS.init(SCCGWA, BPCNHSVR);
        IBS.init(SCCGWA, BPCFPARM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCOUSVR.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOUSVR.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOUSVR.FUNC == 'U') {
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                B030_MODIFY_PROCESS_CN();
                if (pgmRtn) return;
            }
        } else if (BPCOUSVR.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOUSVR.FUNC == 'S') {
            B050_STARTBR_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOUSVR.FUNC == 'N') {
            B050_READNEXT_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOUSVR.FUNC == 'E') {
            B050_ENDBR_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFSVR);
        BPVFSVR.KEY.SVR_NO = BPCOUSVR.KEY.SVR_NO;
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.INFO.POINTER = BPVFSVR;
        BPCFPARM.INFO.FUNC = '3';
        BPCFPARM.INFO.TYPE = "FSVR ";
        S000_CALL_BPZFPARM();
        if (pgmRtn) return;
        S000_CHECK_RETURN();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            R000_TRANS_DATE_OUTPUT_CN();
            if (pgmRtn) return;
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            R000_TRANS_DATE_USVR_FSVR_CN();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.INFO.POINTER = BPVFSVR;
        BPCFPARM.INFO.FUNC = '0';
        BPCFPARM.INFO.TYPE = "FSVR ";
        S000_CALL_BPZFPARM();
        if (pgmRtn) return;
        S000_CHECK_RETURN();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            R000_TRANS_DATE_OUTPUT_CN();
            if (pgmRtn) return;
        }
        B020_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS_CN() throws IOException,SQLException,Exception {
        BPCNHSVR.KEY.SVR_NO = BPCOUSVR.KEY.SVR_NO;
        BPCNHSVR.VAL.EFF_DATE = BPCOUSVR.VAL.EFF_DATE;
        BPCNHSVR.VAL.EXP_DATE = BPCOUSVR.VAL.EXP_DATE;
        BPCNHSVR.VAL.AUT_FLG = BPCOUSVR.VAL.AUT_FLG;
        BPCNHSVR.VAL.DRMCR_FLG = BPCOUSVR.VAL.DRMCR_FLG;
        BPCNHSVR.VAL.MATCH_FLG = BPCOUSVR.VAL.MATCH_FLG;
        if ("1".trim().length() == 0) WS_CNT1 = 0;
        else WS_CNT1 = Short.parseShort("1");
        for (WS_CNT1 = 1; WS_CNT1 <= 20; WS_CNT1 += 1) {
            BPCNHSVR.VAL.FEE_INFO[WS_CNT1-1].FEE_CODE = BPCOUSVR.VAL.DATA[WS_CNT1-1].FEE_CODE;
            BPCNHSVR.VAL.FEE_INFO[WS_CNT1-1].PROD_CODE = BPCOUSVR.VAL.DATA[WS_CNT1-1].PROD_CODE;
            BPCNHSVR.VAL.FEE_INFO[WS_CNT1-1].BVF_CODE = BPCOUSVR.VAL.DATA[WS_CNT1-1].BVF_CODE;
            BPCNHSVR.VAL.FEE_INFO[WS_CNT1-1].ATTR_VAL.BNK_FLG = BPCOUSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.BNK_FLG;
