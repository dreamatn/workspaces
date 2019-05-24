package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3090 {
    int JIBS_tmp_int;
    DBParm BPTVHPB_RD;
    String CPN_S_BV_USE_OUT = "BP-S-BV-USE-OUT ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_R_BRW_TBVD = "BP-R-BRW-TBVD";
    String CPN_S_NUM_CHK = "BP-S-BV-NO-CHK";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_BVNO_LEN = 0;
    long WS_COMP_BEGNO = 0;
    long WS_COMP_ENDNO = 0;
    char WS_TBL_FLAG = ' ';
    String WS_TEMP_PLBOX_NO = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBVUO BPCSBVUO = new BPCSBVUO();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPRTBVD BPRTBVD = new BPRTBVD();
    BPCRTBDB BPCRTBDB = new BPCRTBDB();
    BPCSVBLT BPCSVBLT = new BPCSVBLT();
    BPCSNOCK BPCSNOCK = new BPCSNOCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3020_AWA_3020 BPB3020_AWA_3020;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3090 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3020_AWA_3020>");
        BPB3020_AWA_3020 = (BPB3020_AWA_3020) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BV_PAY_OUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE);
        CEP.TRC(SCCGWA, BPB3020_AWA_3020.BV_DATA[1-1].END_NO);
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE;
        S000_CALL_BPZFBVQU();
        if (BPCFBVQU.TX_DATA.BV_CFLG == 'Y' 
            && BPCFBVQU.TX_DATA.TYPE != '3') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CARD_CANT_PUTT;
            if (BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE);
            S000_ERR_MSG_PROC();
        }
        if (BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.trim().length() > 0) {
            if (BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO == null) BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO = "";
            JIBS_tmp_int = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                WS_FLD_NO = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB3020_AWA_3020.BV_DATA[1-1].END_NO.trim().length() > 0) {
            if (BPB3020_AWA_3020.BV_DATA[1-1].END_NO == null) BPB3020_AWA_3020.BV_DATA[1-1].END_NO = "";
            JIBS_tmp_int = BPB3020_AWA_3020.BV_DATA[1-1].END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3020_AWA_3020.BV_DATA[1-1].END_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPB3020_AWA_3020.BV_DATA[1-1].END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                WS_FLD_NO = BPB3020_AWA_3020.BV_DATA[1-1].END_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
        if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
            if (BPB3020_AWA_3020.BV_DATA[1-1].HEAD_NO.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_HEADNO;
                WS_FLD_NO = BPB3020_AWA_3020.BV_DATA[1-1].HEAD_NO_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.trim().length() > 0 
                || BPB3020_AWA_3020.BV_DATA[1-1].END_NO.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                WS_FLD_NO = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB3020_AWA_3020.BV_DATA[1-1].END_NO.trim().length() > 0) {
            if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
                || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
                if (BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO == null) BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO = "";
                JIBS_tmp_int = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO += " ";
                if (BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
                else WS_COMP_BEGNO = Long.parseLong(BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO.substring(0, WS_BVNO_LEN));
                if (BPB3020_AWA_3020.BV_DATA[1-1].END_NO == null) BPB3020_AWA_3020.BV_DATA[1-1].END_NO = "";
                JIBS_tmp_int = BPB3020_AWA_3020.BV_DATA[1-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3020_AWA_3020.BV_DATA[1-1].END_NO += " ";
                if (BPB3020_AWA_3020.BV_DATA[1-1].END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
                else WS_COMP_ENDNO = Long.parseLong(BPB3020_AWA_3020.BV_DATA[1-1].END_NO.substring(0, WS_BVNO_LEN));
                if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                    WS_FLD_NO = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                }
                IBS.init(SCCGWA, BPCSNOCK);
                BPCSNOCK.BV_CODE = BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE;
                BPCSNOCK.BEG_NO = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO;
                BPCSNOCK.END_NO = BPB3020_AWA_3020.BV_DATA[1-1].END_NO;
                BPCSNOCK.NUM = BPB3020_AWA_3020.BV_DATA[1-1].NUM;
                BPCSNOCK.FUNC = '1';
                S000_CALL_BPZSNOCK();
            }
        } else {
            if (BPCFBVQU.TX_DATA.USE_CTL == '1') {
                IBS.init(SCCGWA, BPRVHPB);
                BPRVHPB.POLL_BOX_IND = BPB3020_AWA_3020.PAY_TYP;
                BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_READ_BPTVHPB();
                CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
                CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
                if (WS_TBL_FLAG == 'Y') {
                    WS_TEMP_PLBOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
                    CEP.TRC(SCCGWA, WS_TEMP_PLBOX_NO);
                } else {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_HVNT_BVB;
                    S000_ERR_MSG_PROC();
                }
                IBS.init(SCCGWA, BPRTBVD);
                BPRTBVD.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPRTBVD.KEY.PL_BOX_NO = WS_TEMP_PLBOX_NO;
                BPRTBVD.KEY.BV_CODE = BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE;
                BPRTBVD.KEY.VALUE = 0;
                BPRTBVD.KEY.HEAD_NO = " ";
                BPRTBVD.KEY.STS = '0';
                IBS.init(SCCGWA, BPCRTBDB);
                BPCRTBDB.INFO.FUNC = '2';
                S000_CALL_BPZRTBDB();
                BPCRTBDB.INFO.FUNC = 'N';
                S000_CALL_BPZRTBDB();
                if (BPCRTBDB.RETURN_INFO == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TBVD_REC_NOTFND;
                    S000_ERR_MSG_PROC();
                }
                BPCRTBDB.INFO.FUNC = 'E';
                S000_CALL_BPZRTBDB();
            }
        }
    }
    public void B020_BV_PAY_OUT() throws IOException,SQLException,Exception {
        if (BPB3020_AWA_3020.BV_DATA[1-1].END_NO.trim().length() > 0 
            || BPCFBVQU.TX_DATA.CTL_FLG == '0') {
            IBS.init(SCCGWA, BPCSBVUO);
            BPCSBVUO.PAY_TYP = BPB3020_AWA_3020.PAY_TYP;
            BPCSBVUO.BV_CODE = BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE;
            BPCSBVUO.BV_NAME = BPB3020_AWA_3020.BV_DATA[1-1].BV_NAME;
            BPCSBVUO.HEAD_NO = BPB3020_AWA_3020.BV_DATA[1-1].HEAD_NO;
            BPCSBVUO.BEG_NO = BPB3020_AWA_3020.BV_DATA[1-1].BEG_NO;
            BPCSBVUO.END_NO = BPB3020_AWA_3020.BV_DATA[1-1].END_NO;
            BPCSBVUO.NUM = BPB3020_AWA_3020.BV_DATA[1-1].NUM;
            BPCSBVUO.REMARK = BPB3020_AWA_3020.REMARK;
            BPCSBVUO.DRW_NM = BPB3020_AWA_3020.DRW_NM;
            BPCSBVUO.DRW_IDT = BPB3020_AWA_3020.DRW_IDT;
            BPCSBVUO.DRW_IDN = BPB3020_AWA_3020.DRW_IDN;
            S000_CALL_BPZSBVUO();
            BPB3020_AWA_3020.MOV_DT = BPCSBVUO.MOV_DT;
            BPB3020_AWA_3020.CONF_NO = BPCSBVUO.CONF_NO;
            BPB3020_AWA_3020.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPB3020_AWA_3020.TR_TLR = SCCGWA.COMM_AREA.TL_ID;
        } else {
            IBS.init(SCCGWA, BPCSVBLT);
            BPCSVBLT.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCSVBLT.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCSVBLT.BV_CODE = BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE;
            BPCSVBLT.NUM = BPB3020_AWA_3020.BV_DATA[1-1].NUM;
            S000_CALL_BPZSVBLT();
            CEP.TRC(SCCGWA, BPCSVBLT.RC.RC_CODE);
            if (BPCSVBLT.RC.RC_CODE != 0) {
                CEP.TRC(SCCGWA, "FAIL");
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NORAML_NO_NOT_ENOUGH);
            } else {
            }
        }
    }
    public void T000_READ_BPTVHPB() throws IOException,SQLException,Exception {
        BPTVHPB_RD = new DBParm();
        BPTVHPB_RD.TableName = "BPTVHPB";
        BPTVHPB_RD.where = "POLL_BOX_IND = :BPRVHPB.POLL_BOX_IND "
            + "AND CUR_TLR = :BPRVHPB.CUR_TLR";
        IBS.READ(SCCGWA, BPRVHPB, this, BPTVHPB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
        }
    }
    public void S000_CALL_BPZRTBDB() throws IOException,SQLException,Exception {
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_TBVD, BPCRTBDB);
        if (BPCRTBDB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTBDB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSNOCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_NUM_CHK, BPCSNOCK);
    }
    public void S000_CALL_BPZSVBLT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-VB-NO-INQ", BPCSVBLT);
        CEP.TRC(SCCGWA, BPCSVBLT.RC);
    }
    public void S000_CALL_BPZSBVUO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BV_USE_OUT, BPCSBVUO);
        if (BPCSBVUO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSBVUO.RC);
            WS_FLD_NO = BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE_NO;
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
