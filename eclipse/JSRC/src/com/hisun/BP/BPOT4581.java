package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4581 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BPX01";
    String CPN_P_QUERY_BANK = "BP-P-QUERY-BANK     ";
    String CPN_P_QUERY_RGND = "BP-P-INQ-RGND ";
    String CPN_BP_ORG_READ = "BP-P-INQ-ORG ";
    String CPN_P_QUERY_RGNC = "BP-P-INQ-RGNC ";
    String CPN_BP_PARM_READ = "BP-PARM-READ  ";
    String CPN_BP_RGND_GROUP = "BP-R-RGND-GROUP ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_TEXT = " ";
    BPOT4581_WS_TYPE_CD WS_TYPE_CD = new BPOT4581_WS_TYPE_CD();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPRGTP BPRPRGTP = new BPRPRGTP();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    BPCORGDO BPCORGDO = new BPCORGDO();
    BPCPQRGD BPCPQRGD = new BPCPQRGD();
    BPCPQRGC BPCPQRGC = new BPCPQRGC();
    BPCRRGDG BPCRRGDG = new BPCRRGDG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BPB4580_AWA_4580 BPB4580_AWA_4580;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCOWA SCCOWA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4581 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4580_AWA_4580>");
        BPB4580_AWA_4580 = (BPB4580_AWA_4580) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_OUTPUT_DATA();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBNK);
        BPCPQBNK.DATA_INFO.BNK = BPB4580_AWA_4580.BNK;
        S000_CALL_BPZPQBNK();
        CEP.TRC(SCCGWA, BPCPQBNK.DATA_INFO.BNK);
        if (BPB4580_AWA_4580.RGN_TYPE.trim().length() == 0 
            || BPB4580_AWA_4580.RGN_TYPE.charAt(0) == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RGN_TYP_NOTFND;
            WS_FLD_NO = BPB4580_AWA_4580.RGN_TYPE_NO;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, BPRPRGTP);
            IBS.init(SCCGWA, BPCPRMR);
            BPRPRGTP.KEY.TYP = "RGNTP";
            if (BPRPRGTP.KEY.CD == null) BPRPRGTP.KEY.CD = "";
            JIBS_tmp_int = BPRPRGTP.KEY.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRGTP.KEY.CD += " ";
            if (BPB4580_AWA_4580.RGN_TYPE == null) BPB4580_AWA_4580.RGN_TYPE = "";
            JIBS_tmp_int = BPB4580_AWA_4580.RGN_TYPE.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) BPB4580_AWA_4580.RGN_TYPE += " ";
            BPRPRGTP.KEY.CD = BPRPRGTP.KEY.CD.substring(0, 4 - 1) + BPB4580_AWA_4580.RGN_TYPE + BPRPRGTP.KEY.CD.substring(4 + BPB4580_AWA_4580.RGN_TYPE.length() - 1);
            if (BPB4580_AWA_4580.BNK == null) BPB4580_AWA_4580.BNK = "";
            JIBS_tmp_int = BPB4580_AWA_4580.BNK.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPB4580_AWA_4580.BNK += " ";
            if (BPRPRGTP.KEY.CD == null) BPRPRGTP.KEY.CD = "";
            JIBS_tmp_int = BPRPRGTP.KEY.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRGTP.KEY.CD += " ";
            BPRPRGTP.KEY.CD = BPB4580_AWA_4580.BNK.substring(0, 2) + BPRPRGTP.KEY.CD.substring(2);
            BPCPRMR.DAT_PTR = BPRPRGTP;
            CEP.TRC(SCCGWA, BPRPRGTP.KEY.CD);
            S000_CALL_BPZPRMR();
        }
        if (BPB4580_AWA_4580.RGN_SEQ == 0 
            || BPB4580_AWA_4580.RGN_SEQ == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RGN_SEQ_NOTFND;
            WS_FLD_NO = BPB4580_AWA_4580.RGN_SEQ_NO;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, BPCPQRGD);
            BPCPQRGD.BNK = BPB4580_AWA_4580.BNK;
            BPCPQRGD.RGN_TYPE = BPB4580_AWA_4580.RGN_TYPE;
            BPCPQRGD.RGN_SEQ = BPB4580_AWA_4580.RGN_SEQ;
            BPCPQRGD.UNIT = BPB4580_AWA_4580.RGN_UNIT;
            S000_CALL_BPZPQRGD();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQRGD.RC);
            if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_EXIST;
                WS_FLD_NO = BPB4580_AWA_4580.RGN_SEQ_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPRPRGTP.DATA_TXT.RPT_ORG_FLG);
        if (BPRPRGTP.DATA_TXT.RPT_ORG_FLG == 'N' 
            && BPRPRGTP.DATA_TXT.UNIT_TYPE == '0') {
            IBS.init(SCCGWA, BPCRRGDG);
            BPCRRGDG.INFO.FUNC = 'G';
            BPCRRGDG.INFO.BNK = BPB4580_AWA_4580.BNK;
            BPCRRGDG.INFO.RGN_TYPE = BPB4580_AWA_4580.RGN_TYPE;
            if (BPB4580_AWA_4580.RGN_UNIT == null) BPB4580_AWA_4580.RGN_UNIT = "";
            JIBS_tmp_int = BPB4580_AWA_4580.RGN_UNIT.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB4580_AWA_4580.RGN_UNIT += " ";
            if (BPB4580_AWA_4580.RGN_UNIT.substring(0, 6).trim().length() == 0) BPCRRGDG.INFO.BR = 0;
            else BPCRRGDG.INFO.BR = Integer.parseInt(BPB4580_AWA_4580.RGN_UNIT.substring(0, 6));
            S000_CALL_BPZTRGDG();
            if (BPCRRGDG.RETURN_CNT > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SAME_BR_PROHIBITED;
                WS_FLD_NO = BPB4580_AWA_4580.RGN_UNIT_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB4580_AWA_4580.RGN_UNIT.trim().length() == 0 
            || BPB4580_AWA_4580.RGN_UNIT.charAt(0) == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
            WS_FLD_NO = BPB4580_AWA_4580.RGN_UNIT_NO;
            S000_ERR_MSG_PROC();
        } else {
            if (BPRPRGTP.DATA_TXT.UNIT_TYPE == '0') {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BNK = BPB4580_AWA_4580.BNK;
                if (BPB4580_AWA_4580.RGN_UNIT == null) BPB4580_AWA_4580.RGN_UNIT = "";
                JIBS_tmp_int = BPB4580_AWA_4580.RGN_UNIT.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB4580_AWA_4580.RGN_UNIT += " ";
                if (BPB4580_AWA_4580.RGN_UNIT.substring(0, 6).trim().length() == 0) BPCPQORG.BR = 0;
                else BPCPQORG.BR = Integer.parseInt(BPB4580_AWA_4580.RGN_UNIT.substring(0, 6));
                CEP.TRC(SCCGWA, BPCPQORG.BR);
                S000_CALL_BPZPQORG();
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
                    WS_FLD_NO = BPB4580_AWA_4580.BR_NO;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        IBS.init(SCCGWA, BPCPQRGC);
        BPCPQRGC.BNK = BPB4580_AWA_4580.BNK;
        BPCPQRGC.RGN_NO.RGN_TYPE = BPB4580_AWA_4580.RGN_TYPE;
        BPCPQRGC.RGN_NO.RGN_SEQ = BPB4580_AWA_4580.RGN_SEQ;
        S000_CALL_BPZPQRGC();
        IBS.init(SCCGWA, BPCPQRGD);
        BPCPQRGD.BNK = BPB4580_AWA_4580.BNK;
        BPCPQRGD.RGN_TYPE = BPB4580_AWA_4580.RGN_TYPE;
        BPCPQRGD.RGN_SEQ = BPB4580_AWA_4580.RGN_SEQ;
        BPCPQRGD.UNIT = BPB4580_AWA_4580.RGN_UNIT;
        S000_CALL_BPZPQRGD();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQRGD.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_EXIST;
            WS_FLD_NO = BPB4580_AWA_4580.RGN_SEQ_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4580_AWA_4580.EFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LESS_ACDATE;
            WS_FLD_NO = BPB4580_AWA_4580.EFF_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB4580_AWA_4580.EFF_DT > BPB4580_AWA_4580.EXP_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            WS_FLD_NO = BPB4580_AWA_4580.EFF_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB4580_AWA_4580.EXP_DT < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LESS_ACDATE;
            WS_FLD_NO = BPB4580_AWA_4580.EXP_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_OUTPUT_DATA() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_OUPUT();
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCORGDO;
        SCCFMT.DATA_LEN = 185;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_END_ERROR() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCORGDO.BNK = BPB4580_AWA_4580.BNK;
        BPCORGDO.RGN_TYPE = BPB4580_AWA_4580.RGN_TYPE;
        BPCORGDO.RGN_SEQ = BPB4580_AWA_4580.RGN_SEQ;
        BPCORGDO.UNIT = BPB4580_AWA_4580.RGN_UNIT;
        BPCORGDO.FLG = BPB4580_AWA_4580.FLG;
        BPCORGDO.RMK = BPB4580_AWA_4580.RMK;
        BPCORGDO.EFF_DT = BPB4580_AWA_4580.EFF_DT;
        BPCORGDO.EXP_DT = BPB4580_AWA_4580.EXP_DT;
        BPCORGDO.UPT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCORGDO.UPT_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void S000_CALL_BPZPQBNK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QUERY_BANK, BPCPQBNK);
        if (BPCPQBNK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            WS_FLD_NO = BPB4580_AWA_4580.BNK_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQRGD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QUERY_RGND, BPCPQRGD);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPQRGD.RC);
        if (BPCPQRGD.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQRGD.RC);
            WS_FLD_NO = BPB4580_AWA_4580.BNK_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_ORG_READ, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            WS_FLD_NO = BPB4580_AWA_4580.BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_PARM_READ, BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RGN_TYP_NOTFND;
            WS_FLD_NO = BPB4580_AWA_4580.RGN_TYPE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTRGDG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_RGND_GROUP, BPCRRGDG);
        if (BPCRRGDG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRRGDG.RC);
            WS_FLD_NO = BPB4580_AWA_4580.BNK_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQRGC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QUERY_RGNC, BPCPQRGC);
        if (BPCPQRGC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQRGC.RC);
            WS_FLD_NO = BPB4580_AWA_4580.RGN_SEQ_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
