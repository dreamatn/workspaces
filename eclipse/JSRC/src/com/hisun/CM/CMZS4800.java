package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.TD.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DD.*;
import com.hisun.TC.*;
import com.hisun.TC.TCCTOA;

import java.io.IOException;
import java.sql.SQLException;

public class CMZS4800 {
    DBParm TCTLOGR1_RD;
    DBParm TCTLOGR2_RD;
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_R = 0;
    String WS_FRM_APP = " ";
    int WS_LEN = 0;
    CMZS4800_WS_BV_NO WS_BV_NO = new CMZS4800_WS_BV_NO();
    String WS_TEXT = " ";
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    TDCBVCHG TDCBVCHG = new TDCBVCHG();
    BPCFQFBV BPCFQFBV = new BPCFQFBV();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    CICQACRI CICQACRI = new CICQACRI();
    DDCSCHPB DDCSCHPB = new DDCSCHPB();
    DDCIPSBK DDCIPSBK = new DDCIPSBK();
    TDCACE TDCACE = new TDCACE();
    DDCIMMST DDCIMMST = new DDCIMMST();
    CMCF480 CMCF480 = new CMCF480();
    TCCTOA TCCTOA = new TCCTOA();
    TCRLOGR TCRLOGR = new TCRLOGR();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMCS4800 CMCS4800;
    public void MP(SCCGWA SCCGWA, CMCS4800 CMCS4800) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS4800 = CMCS4800;
        CEP.TRC(SCCGWA);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CMCF480);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B050_INPUT_CHECK();
        if (pgmRtn) return;
        B100_INQ_MAIN_PROC();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B050_INPUT_CHECK() throws IOException,SQLException,Exception {
        if (CMCS4800.FUNC == '1') {
            for (WS_I = 1; CMCS4800.AC[WS_I-1].CUS_AC.trim().length() != 0 
                && WS_I <= 20; WS_I += 1) {
                if (CMCS4800.AC[WS_I-1].CUS_AC.trim().length() == 0) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_ERR);
                }
                if (CMCS4800.BV_CODE.trim().length() == 0) {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_BVC_ERR);
                }
                if (CMCS4800.BV_TYP == ' ') {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_BVT_ERR);
                }
            }
        }
    }
    public void B100_INQ_MAIN_PROC() throws IOException,SQLException,Exception {
        if (CMCS4800.FUNC == '1') {
            for (WS_I = 1; CMCS4800.AC[WS_I-1].CUS_AC.trim().length() != 0 
                && WS_I <= 200; WS_I += 1) {
                B110_FETCH_MIN_BV_NO();
                if (pgmRtn) return;
                CICQACRI.FUNC = 'A';
                CICQACRI.DATA.AGR_NO = CMCS4800.AC[WS_I-1].CUS_AC;
                S000_CALL_CIZQACRI();
                if (pgmRtn) return;
                WS_FRM_APP = CICQACRI.O_DATA.O_FRM_APP;
                if (WS_FRM_APP.equalsIgnoreCase("TD")) {
                    B130_TD_BV_BIND_PROC();
                    if (pgmRtn) return;
                    B135_INQ_AC();
                    if (pgmRtn) return;
                    B150_REV_OCAC();
                    if (pgmRtn) return;
                    CMCF480.BV[WS_I-1].BV_NO = WS_BV_NO.WS_ARRAY[WS_I-1].WS_BEG_NO;
                } else if (WS_FRM_APP.equalsIgnoreCase("DD")) {
                    B140_DD_BV_BIND_PROC();
                    if (pgmRtn) return;
                    B150_REV_OCAC();
                    if (pgmRtn) return;
                    CMCF480.BV[WS_I-1].BV_NO = WS_BV_NO.WS_ARRAY[WS_I-1].WS_BEG_NO;
                } else {
                }
            }
        } else {
            B160_READ_TCTLOG();
            if (pgmRtn) return;
        }
        B170_OUT_PUT();
        if (pgmRtn) return;
    }
    public void B110_FETCH_MIN_BV_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFQFBV);
        BPCFQFBV.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCFQFBV.TYPE = '0';
        BPCFQFBV.BV_CODE = CMCS4800.BV_CODE;
        R000_CALL_BPZFQFBV();
        if (pgmRtn) return;
        WS_BV_NO.WS_ARRAY[WS_I-1].WS_BEG_NO = BPCFQFBV.BEG_NO;
        CEP.TRC(SCCGWA, BPCFQFBV.BEG_NO);
    }
    public void B120_BV_USE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUBUSE);
        BPCUBUSE.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBUSE.TYPE = '0';
        BPCUBUSE.BV_CODE = CMCS4800.BV_CODE;
        BPCUBUSE.NUM = 1;
        BPCUBUSE.BEG_NO = WS_BV_NO.WS_ARRAY[WS_I-1].WS_BEG_NO;
        BPCUBUSE.END_NO = WS_BV_NO.WS_ARRAY[WS_I-1].WS_BEG_NO;
        BPCUBUSE.EC_IND = '1';
        R000_CALL_BPZUBUSE();
        if (pgmRtn) return;
    }
    public void B130_TD_BV_BIND_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCBVCHG);
        TDCBVCHG.CHG_TYP = '5';
        TDCBVCHG.BV_CD = CMCS4800.BV_CODE;
        TDCBVCHG.BV_TYP = CMCS4800.BV_TYP;
        TDCBVCHG.BV_NO_NEW = WS_BV_NO.WS_ARRAY[WS_I-1].WS_BEG_NO;
        TDCBVCHG.AC_NO = CMCS4800.AC[WS_I-1].CUS_AC;
        TDCBVCHG.AC_SEQ = 1;
        R000_CALL_TDZBVCHG();
        if (pgmRtn) return;
    }
    public void B135_INQ_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = CMCS4800.AC[WS_I-1].CUS_AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
    }
    public void B140_DD_BV_BIND_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIPSBK);
        DDCIPSBK.FUNC = 'C';
        DDCIPSBK.CHA_REASON = 'I';
        DDCIPSBK.AC = CMCS4800.AC[WS_I-1].CUS_AC;
        DDCIPSBK.PSBK_NO = WS_BV_NO.WS_ARRAY[WS_I-1].WS_BEG_NO;
        DDCIPSBK.PSBK_NO_NEW = WS_BV_NO.WS_ARRAY[WS_I-1].WS_BEG_NO;
        DDCIPSBK.VCH_TYPE = CMCS4800.BV_TYP;
        DDCIPSBK.PAY_MTH = '1';
        R000_CALL_DDZIPSBK();
        if (pgmRtn) return;
    }
    public void B150_REV_OCAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.STS = 'B';
        BPCSOCAC.AC = CMCS4800.AC[WS_I-1].CUS_AC;
        BPCSOCAC.FUNC = 'U';
        BPCSOCAC.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        BPCSOCAC.BV_NO = WS_BV_NO.WS_ARRAY[WS_I-1].WS_BEG_NO;
        R000_CALL_BPZSOCAC();
        if (pgmRtn) return;
    }
    public void B160_READ_TCTLOG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TCRLOGR);
        CEP.TRC(SCCGWA, CMCS4800.REQ_JRN);
        TCRLOGR.KEY.REQ_SYS_JRN = CMCS4800.REQ_JRN;
        TCTLOGR1_RD = new DBParm();
        TCTLOGR1_RD.TableName = "TCTLOGR1";
        TCTLOGR1_RD.where = "REQ_SYS_JRN = :TCRLOGR.KEY.REQ_SYS_JRN";
        IBS.READ(SCCGWA, TCRLOGR, this, TCTLOGR1_RD);
        CEP.TRC(SCCGWA, "111");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            TCTLOGR2_RD = new DBParm();
            TCTLOGR2_RD.TableName = "TCTLOGR2";
            TCTLOGR2_RD.where = "REQ_SYS_JRN = :TCRLOGR.KEY.REQ_SYS_JRN";
            IBS.READ(SCCGWA, TCRLOGR, this, TCTLOGR2_RD);
            CEP.TRC(SCCGWA, "22222");
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_JRN_ERR);
            } else {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_JRN_ERR);
            }
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_JRN_ERR);
        }
        if (TCRLOGR.TR_STS == '0') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TRSTS_ERR);
        }
        IBS.CPY2CLS(SCCGWA, TCRLOGR.BLOB_TOA_REC, TCCTOA);
        WS_LEN = 4000;
        if (TCCTOA.SERV_OUTP_AREA.OUTP_DATA == null) TCCTOA.SERV_OUTP_AREA.OUTP_DATA = "";
        JIBS_tmp_int = TCCTOA.SERV_OUTP_AREA.OUTP_DATA.length();
        for (int i=0;i<15360-JIBS_tmp_int;i++) TCCTOA.SERV_OUTP_AREA.OUTP_DATA += " ";
        IBS.CPY2CLS(SCCGWA, TCCTOA.SERV_OUTP_AREA.OUTP_DATA.substring(11 - 1, 11 + WS_LEN - 1), CMCF480);
    }
    public void B170_OUT_PUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CM480";
        SCCFMT.DATA_PTR = CMCF480;
        SCCFMT.DATA_LEN = 4000;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CALL_BPZFQFBV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-INQ-FST-BVNO", BPCFQFBV, true);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC, true);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void R000_CALL_BPZUBUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-TLR-BV-USE", BPCUBUSE, true);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI, true);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void R000_CALL_TDZBVCHG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BV-CHG", TDCBVCHG, true);
        CEP.TRC(SCCGWA, TDCBVCHG.RC_MSG.RC_CD);
        if (TDCBVCHG.RC_MSG.RC_CD != 0) {
            CEP.ERR(SCCGWA, TDCBVCHG.RC_MSG);
        }
    }
    public void R000_CALL_DDZIPSBK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-PSBK-PROC", DDCIPSBK, true);
        CEP.TRC(SCCGWA, DDCIPSBK.RC.RC_CODE);
        if (DDCIPSBK.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCIPSBK.RC);
        }
    }
    public void R000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC, true);
        CEP.TRC(SCCGWA, BPCSOCAC.RC.RC_CODE);
        if (BPCSOCAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCSOCAC.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
