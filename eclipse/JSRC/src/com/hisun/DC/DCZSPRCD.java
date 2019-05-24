package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSPRCD {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    brParm DCTMPRD_BR = new brParm();
    DBParm DCTMPRD_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC999";
    int WS_OUT_CNT = 0;
    String WS_MSGID = " ";
    short WS_FLD_NO = 0;
    char WS_MPRD_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DCCOPARM DCCOPARM = new DCCOPARM();
    DCRMPRD DCRMPRD = new DCRMPRD();
    SCCGWA SCCGWA;
    DCCSPRCD DCCSPRCD;
    public void MP(SCCGWA SCCGWA, DCCSPRCD DCCSPRCD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSPRCD = DCCSPRCD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSPRCD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (DCCSPRCD.FUNC == 'I') {
            B200_INQ_PARM_CD_PROC();
            if (pgmRtn) return;
        } else if (DCCSPRCD.FUNC == 'A') {
            B300_ADD_PARM_CD_PROC();
            if (pgmRtn) return;
        } else if (DCCSPRCD.FUNC == 'M') {
            B400_MOD_PARM_CD_PROC();
            if (pgmRtn) return;
        } else if (DCCSPRCD.FUNC == 'D') {
            B500_DEL_PARM_CD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID TX TYPE(" + DCCSPRCD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSPRCD.FUNC);
        CEP.TRC(SCCGWA, DCCSPRCD.BANK_NO);
        CEP.TRC(SCCGWA, DCCSPRCD.PRDMO_CD);
        CEP.TRC(SCCGWA, DCCSPRCD.PARM_CODE);
        CEP.TRC(SCCGWA, DCCSPRCD.EFF_DATE);
        if (DCCSPRCD.FUNC == ' ') {
            WS_MSGID = DCCMSG_ERROR_MSG.DC_FUNC_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DCCSPRCD.FUNC != 'A' 
            && DCCSPRCD.FUNC != 'D' 
            && DCCSPRCD.FUNC != 'M' 
            && DCCSPRCD.FUNC != 'I') {
            WS_MSGID = DCCMSG_ERROR_MSG.DC_FUNC_INVALD;
            if (DCCSPRCD.FUNC == ' ') WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(""+DCCSPRCD.FUNC);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DCCSPRCD.BANK_NO.trim().length() == 0) {
            WS_MSGID = DCCMSG_ERROR_MSG.DC_BANK_NO_M_INPUT;
            if (DCCSPRCD.BANK_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DCCSPRCD.BANK_NO);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DCCSPRCD.PRDMO_CD.trim().length() == 0) {
            WS_MSGID = DCCMSG_ERROR_MSG.DC_PRDMO_CD_M_INPUT;
            if (DCCSPRCD.PRDMO_CD.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DCCSPRCD.PRDMO_CD);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DCCSPRCD.PARM_CODE.trim().length() == 0) {
            WS_MSGID = DCCMSG_ERROR_MSG.DC_PARM_CODE_M_INPUT;
            if (DCCSPRCD.PARM_CODE.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DCCSPRCD.PARM_CODE);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DCCSPRCD.EFF_DATE == 0) {
            WS_MSGID = DCCMSG_ERROR_MSG.DC_EFF_DATE_M_INPUT;
            WS_FLD_NO = (short) DCCSPRCD.EFF_DATE;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_MSGID = DCCMSG_ERROR_MSG.DC_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B200_INQ_PARM_CD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRMPRD);
        DCRMPRD.KEY.IBS_AC_BK = DCCSPRCD.BANK_NO;
        DCRMPRD.KEY.PRDMO_CD = DCCSPRCD.PRDMO_CD;
        DCRMPRD.KEY.PARM_CODE = DCCSPRCD.PARM_CODE;
        DCRMPRD.KEY.EFF_DATE = DCCSPRCD.EFF_DATE;
        R000_STARTBR_FIRST_DCTMPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_MPRD_FLG);
        if (WS_MPRD_FLG == 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PRD_REC_NOTFND);
        } else {
            IBS.init(SCCGWA, DCCOPARM);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCRMPRD);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCOPARM);
            B600_OUTPUT_DATA();
            if (pgmRtn) return;
        }
    }
    public void B300_ADD_PARM_CD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRMPRD);
        DCRMPRD.KEY.IBS_AC_BK = DCCSPRCD.BANK_NO;
        DCRMPRD.KEY.PRDMO_CD = DCCSPRCD.PRDMO_CD;
        DCRMPRD.KEY.PARM_CODE = DCCSPRCD.PARM_CODE;
        DCRMPRD.KEY.EFF_DATE = DCCSPRCD.EFF_DATE;
        DCRMPRD.EXP_DATE = DCCSPRCD.EXP_DATE;
        DCRMPRD.CDESC = DCCSPRCD.CDESC;
        DCRMPRD.ADSC_TYPE = DCCSPRCD.ADSC_TYPE;
        DCRMPRD.DD_PROD = DCCSPRCD.DD_PROD;
        DCRMPRD.CARD_PHY_TYP = DCCSPRCD.CARD_PHY_TYP;
        DCRMPRD.JOIN_CUS_FLG = DCCSPRCD.JOIN_CUS_FLG;
        DCRMPRD.AC_HANG_FLG = DCCSPRCD.AC_HANG_FLG;
        DCRMPRD.SUP_CARD_FLG = DCCSPRCD.SUP_CARD_FLG;
        DCRMPRD.MOBL_PAY_FLG = DCCSPRCD.MOBL_PAY_FLG;
        DCRMPRD.ANU_FEE_FREE = DCCSPRCD.ANUE_FEE_FREE;
        DCRMPRD.DFT_EXP = DCCSPRCD.DFT_EXP;
        DCRMPRD.SVRLT_CD = DCCSPRCD.SVRLT_CD;
        DCRMPRD.ADESC = DCCSPRCD.ADESC;
        DCRMPRD.CARD_TYP = DCCSPRCD.CARD_TYP;
        DCRMPRD.IC_APP_TYP = "" + DCCSPRCD.IC_APP_TYP;
        JIBS_tmp_int = DCRMPRD.IC_APP_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) DCRMPRD.IC_APP_TYP = "0" + DCRMPRD.IC_APP_TYP;
        DCRMPRD.IC_APP_FLG = DCCSPRCD.IC_APP_FLG;
        DCRMPRD.IC_PROD_CD = DCCSPRCD.IC_PROD_CD;
        DCRMPRD.CARD_CLS = DCCSPRCD.CARD_CLS;
        DCRMPRD.SUP_CARD_NUM = DCCSPRCD.SUP_CARD_NUM;
        DCRMPRD.FUNC_FLG = DCCSPRCD.FUNC_FLG;
        DCRMPRD.FACE_FLG = DCCSPRCD.FACE_FLG;
        DCRMPRD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRMPRD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRMPRD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRMPRD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        R000_WRITE_DCTMPRD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCCOPARM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCRMPRD);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCOPARM);
        B600_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B400_MOD_PARM_CD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRMPRD);
        DCRMPRD.KEY.IBS_AC_BK = DCCSPRCD.BANK_NO;
        DCRMPRD.KEY.PRDMO_CD = DCCSPRCD.PRDMO_CD;
        DCRMPRD.KEY.PARM_CODE = DCCSPRCD.PARM_CODE;
        DCRMPRD.KEY.EFF_DATE = DCCSPRCD.EFF_DATE;
        R000_READ_DCTMPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_MPRD_FLG);
        if (WS_MPRD_FLG == 'N') {
            CEP.TRC(SCCGWA, "--PARMCODE NOTFND--");
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PRD_REC_NOTFND);
        } else {
            CEP.TRC(SCCGWA, "--ADD A NEW RECORD--");
            B300_ADD_PARM_CD_PROC();
            if (pgmRtn) return;
        }
    }
    public void B500_DEL_PARM_CD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRMPRD);
        DCRMPRD.KEY.IBS_AC_BK = DCCSPRCD.BANK_NO;
        DCRMPRD.KEY.PRDMO_CD = DCCSPRCD.PRDMO_CD;
        DCRMPRD.KEY.PARM_CODE = DCCSPRCD.PARM_CODE;
        DCRMPRD.KEY.EFF_DATE = DCCSPRCD.EFF_DATE;
        R000_READUPDATE_DCTMPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_MPRD_FLG);
        if (WS_MPRD_FLG == 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PRD_REC_NOTFND);
        } else {
            CEP.TRC(SCCGWA, DCRMPRD.EXP_DATE);
            if (DCRMPRD.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PRDREC_ALR_EXP);
            } else {
                DCRMPRD.EXP_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRMPRD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRMPRD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                R000_REWRITE_DCTMPRD();
                if (pgmRtn) return;
                IBS.init(SCCGWA, DCCOPARM);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCRMPRD);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCOPARM);
                B600_OUTPUT_DATA();
                if (pgmRtn) return;
            }
        }
    }
    public void B600_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCOPARM;
        SCCFMT.DATA_LEN = 191;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_STARTBR_FIRST_DCTMPRD() throws IOException,SQLException,Exception {
        DCTMPRD_BR.rp = new DBParm();
        DCTMPRD_BR.rp.TableName = "DCTMPRD";
        DCTMPRD_BR.rp.where = "IBS_AC_BK = :DCRMPRD.KEY.IBS_AC_BK "
            + "AND PRDMO_CD = :DCRMPRD.KEY.PRDMO_CD "
            + "AND PARM_CODE = :DCRMPRD.KEY.PARM_CODE "
            + "AND EFF_DATE <= :DCRMPRD.KEY.EFF_DATE";
        DCTMPRD_BR.rp.fst = true;
        DCTMPRD_BR.rp.order = "EFF_DATE DESC";
        IBS.STARTBR(SCCGWA, DCRMPRD, this, DCTMPRD_BR);
    }
    public void R000_WRITE_DCTMPRD() throws IOException,SQLException,Exception {
        DCTMPRD_RD = new DBParm();
        DCTMPRD_RD.TableName = "DCTMPRD";
        IBS.WRITE(SCCGWA, DCRMPRD, DCTMPRD_RD);
    }
    public void R000_READ_DCTMPRD() throws IOException,SQLException,Exception {
        DCTMPRD_RD = new DBParm();
        DCTMPRD_RD.TableName = "DCTMPRD";
        IBS.READ(SCCGWA, DCRMPRD, DCTMPRD_RD);
    }
    public void R000_READUPDATE_DCTMPRD() throws IOException,SQLException,Exception {
        DCTMPRD_RD = new DBParm();
        DCTMPRD_RD.TableName = "DCTMPRD";
        DCTMPRD_RD.upd = true;
        IBS.READ(SCCGWA, DCRMPRD, DCTMPRD_RD);
    }
    public void R000_REWRITE_DCTMPRD() throws IOException,SQLException,Exception {
        DCTMPRD_RD = new DBParm();
        DCTMPRD_RD.TableName = "DCTMPRD";
        IBS.REWRITE(SCCGWA, DCRMPRD, DCTMPRD_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_MSGID, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, WS_FLD_NO);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
