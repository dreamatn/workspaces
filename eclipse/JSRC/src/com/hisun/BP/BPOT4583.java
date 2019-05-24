package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4583 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP421";
    String CPN_BANK_MAINTAIN = "BP-S-RGND-MAINTAIN  ";
    String CPN_P_QUERY_RGND = "BP-P-INQ-RGND ";
    String CPN_BP_ORG_READ = "BP-P-INQ-ORG ";
    String CPN_P_QUERY_RGNC = "BP-P-INQ-RGNC ";
    String CPN_BP_PARM_READ = "BP-PARM-READ  ";
    String CPN_BP_RGND_GROUP = "BP-R-RGND-GROUP ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT4583_WS_TYPE_CD WS_TYPE_CD = new BPOT4583_WS_TYPE_CD();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPRGTP BPRPRGTP = new BPRPRGTP();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCSRGND BPCSRGND = new BPCSRGND();
    BPCPQRGD BPCPQRGD = new BPCPQRGD();
    BPCPQRGC BPCPQRGC = new BPCPQRGC();
    BPCRRGDG BPCRRGDG = new BPCRRGDG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BPB4580_AWA_4580 BPB4580_AWA_4580;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4583 return!");
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
        B020_MODIFY_RGND_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4580_AWA_4580.RGN_TYPE.trim().length() == 0 
            || BPB4580_AWA_4580.RGN_TYPE.charAt(0) == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RGN_TYP_NOTFND;
            WS_FLD_NO = BPB4580_AWA_4580.RGN_TYPE_NO;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, BPRPRGTP);
            IBS.init(SCCGWA, BPCPRMR);
            BPRPRGTP.KEY.TYP = "RGNTP";
            BPRPRGTP.KEY.CD = BPB4580_AWA_4580.RGN_TYPE;
            CEP.TRC(SCCGWA, BPRPRGTP);
            BPCPRMR.DAT_PTR = BPRPRGTP;
            S000_CALL_BPZPRMR();
        }
        CEP.TRC(SCCGWA, "CHECK RGN-TYPE END");
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
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFOUND;
                WS_FLD_NO = BPB4580_AWA_4580.RGN_SEQ_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, "CHECK RGN-SEQ  END");
        if (BPB4580_AWA_4580.RGN_UNIT.trim().length() == 0 
            || BPB4580_AWA_4580.RGN_UNIT.charAt(0) == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
            WS_FLD_NO = BPB4580_AWA_4580.RGN_UNIT_NO;
            S000_ERR_MSG_PROC();
        } else {
            if (BPB4580_AWA_4580.UNT_TYPE == '0') {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BNK = BPB4580_AWA_4580.BNK;
                if (BPB4580_AWA_4580.RGN_UNIT == null) BPB4580_AWA_4580.RGN_UNIT = "";
                JIBS_tmp_int = BPB4580_AWA_4580.RGN_UNIT.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB4580_AWA_4580.RGN_UNIT += " ";
                if (BPB4580_AWA_4580.RGN_UNIT.substring(0, 9).trim().length() == 0) BPCPQORG.BR = 0;
                else BPCPQORG.BR = Integer.parseInt(BPB4580_AWA_4580.RGN_UNIT.substring(0, 9));
                S000_CALL_BPZPQORG();
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
                    WS_FLD_NO = BPB4580_AWA_4580.RGN_UNIT_NO;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        CEP.TRC(SCCGWA, "CHECK UNIT     END");
        IBS.init(SCCGWA, BPCPQRGC);
        BPCPQRGC.BNK = BPB4580_AWA_4580.BNK;
        BPCPQRGC.RGN_NO.RGN_TYPE = BPB4580_AWA_4580.RGN_TYPE;
        BPCPQRGC.RGN_NO.RGN_SEQ = BPB4580_AWA_4580.RGN_SEQ;
        S000_CALL_BPZPQRGC();
        if (BPB4580_AWA_4580.EFF_DT > SCCGWA.COMM_AREA.TR_DATE) {
            if (BPB4580_AWA_4580.EFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LESS_ACDATE;
                WS_FLD_NO = BPB4580_AWA_4580.EFF_DT_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB4580_AWA_4580.EFF_DT > BPB4580_AWA_4580.EXP_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            WS_FLD_NO = BPB4580_AWA_4580.EFF_DT_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_MODIFY_RGND_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSRGND);
        BPCSRGND.FUNC = 'M';
        BPCSRGND.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSRGND();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSRGND.BNK = BPB4580_AWA_4580.BNK;
        BPCSRGND.RGN_TYPE = BPB4580_AWA_4580.RGN_TYPE;
        BPCSRGND.RGN_SEQ = BPB4580_AWA_4580.RGN_SEQ;
        BPCSRGND.UNIT = BPB4580_AWA_4580.RGN_UNIT;
        BPCSRGND.FLG = BPB4580_AWA_4580.FLG;
        BPCSRGND.RMK = BPB4580_AWA_4580.RMK;
        BPCSRGND.EFF_DT = BPB4580_AWA_4580.EFF_DT;
        BPCSRGND.EXP_DT = BPB4580_AWA_4580.EXP_DT;
        BPCSRGND.UPT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSRGND.UPT_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_ORG_READ, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            WS_FLD_NO = BPB4580_AWA_4580.BR_NO;
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
    public void S000_CALL_BPZPQRGC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QUERY_RGNC, BPCPQRGC);
        if (BPCPQRGC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQRGC.RC);
            WS_FLD_NO = BPB4580_AWA_4580.BNK_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_PARM_READ, BPCPRMR);
        CEP.TRC(SCCGWA, "PRMR-RC-RTNCODE:");
        CEP.TRC(SCCGWA, BPCPRMR.RC.RC_RTNCODE);
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
    public void S000_CALL_BPZSRGND() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_BANK_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSRGND;
        SCCCALL.ERR_FLDNO = BPB4580_AWA_4580.BNK_NO;
        IBS.CALL(SCCGWA, SCCCALL);
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