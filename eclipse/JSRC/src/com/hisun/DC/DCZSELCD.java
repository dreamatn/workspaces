package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.EA.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSELCD {
    int JIBS_tmp_int;
    DBParm DCTDCCLS_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_HIS_RMK = "OPEN A ELE CARD ";
    String K_OUTPUT_FMT = "DC580";
    String K_DC_IN_CI_TYPE = "DC";
    String K_DD_IN_CI_TYPE = "DD";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    long WS_CARD_SEQNO = 0;
    String WS_CARDNO0 = " ";
    DCZSELCD_WS_OUT_PUTPUT WS_OUT_PUTPUT = new DCZSELCD_WS_OUT_PUTPUT();
    String WS_CARD_CLS_CD = " ";
    String WS_BV_CD_NO = " ";
    int WS_CNT_T = 0;
    char WS_OPEN_CARD_FLG = ' ';
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCCUOPEN DCCUOPEN = new DCCUOPEN();
    CICCUST CICCUST = new CICCUST();
    EACSBIND EACSBIND = new EACSBIND();
    DCCOELCD DCCOELCD = new DCCOELCD();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    CICQACRI CICQACRI = new CICQACRI();
    DCRDCCLS DCRDCCLS = new DCRDCCLS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSELCD DCCSELCD;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, DCCSELCD DCCSELCD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSELCD = DCCSELCD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSELCD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_HOLDER_CI_INF();
        if (pgmRtn) return;
        if (DCCSELCD.BIND_AC.trim().length() > 0 
            && DCCSELCD.IO_FLG == 'I') {
            B060_BIND_AC_CHK();
            if (pgmRtn) return;
        }
        B030_TRANS_DATA_DCZUOPEN();
        if (pgmRtn) return;
        if (DCCSELCD.BIND_AC.trim().length() > 0) {
            B040_AC_BIND();
            if (pgmRtn) return;
        }
        B050_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSELCD.CI_NO);
        CEP.TRC(SCCGWA, DCCSELCD.PROD_CD);
        CEP.TRC(SCCGWA, DCCSELCD.AC_NM);
        CEP.TRC(SCCGWA, DCCSELCD.AC_TYP);
        CEP.TRC(SCCGWA, DCCSELCD.FACE_FLG);
        CEP.TRC(SCCGWA, DCCSELCD.PSW1);
        CEP.TRC(SCCGWA, DCCSELCD.PSW2);
        CEP.TRC(SCCGWA, DCCSELCD.IO_FLG);
        CEP.TRC(SCCGWA, DCCSELCD.OTH_BNK);
        CEP.TRC(SCCGWA, DCCSELCD.OTH_BNK_NME);
        CEP.TRC(SCCGWA, DCCSELCD.BIND_AC);
        if (DCCSELCD.CI_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CINO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSELCD.PROD_CD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRDCCLS);
        DCRDCCLS.KEY.CARD_PROD_CD = DCCSELCD.PROD_CD;
        T000_READ_FIRST_DCTDCCLS();
        if (pgmRtn) return;
        WS_CARD_CLS_CD = DCRDCCLS.KEY.CARD_CLS_CD;
        WS_BV_CD_NO = DCRDCCLS.BV_CD_NO;
        if (DCCSELCD.PSW2.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VPSW_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSELCD.AC_TYP != '2' 
            && DCCSELCD.AC_TYP != '3') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_TYPE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_HOLDER_CI_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CFX010");
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = DCCSELCD.CI_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
    }
    public void B030_TRANS_DATA_DCZUOPEN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUOPEN);
        DCCUOPEN.CARD_PROD_CD = DCCSELCD.PROD_CD;
        DCCUOPEN.HOLDER_CINO = DCCSELCD.CI_NO;
        DCCUOPEN.HOLDER_IDTYP = CICCUST.O_DATA.O_ID_TYPE;
        DCCUOPEN.HOLDER_IDNO = CICCUST.O_DATA.O_ID_NO;
        DCCUOPEN.HOLDER_NAME = DCCSELCD.AC_NM;
        DCCUOPEN.OWNER_CINO = DCCSELCD.CI_NO;
        DCCUOPEN.AC_TYP = 'A';
        DCCUOPEN.CARD_LNK_TYP = '1';
        DCCUOPEN.AC_TYPE = DCCSELCD.AC_TYP;
        DCCUOPEN.FACE_FLG = DCCSELCD.FACE_FLG;
        DCCUOPEN.PSW = DCCSELCD.PSW2;
        if (DCCUOPEN.TRT_CTLW == null) DCCUOPEN.TRT_CTLW = "";
        JIBS_tmp_int = DCCUOPEN.TRT_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DCCUOPEN.TRT_CTLW += " ";
        DCCUOPEN.TRT_CTLW = DCCUOPEN.TRT_CTLW.substring(0, 2 - 1) + "1" + DCCUOPEN.TRT_CTLW.substring(2 + 1 - 1);
        DCCUOPEN.SNAME_TRAN_FLG = 'Y';
        DCCUOPEN.CARD_CLS_PROD = WS_CARD_CLS_CD;
        DCCUOPEN.BV_CD_NO = WS_BV_CD_NO;
        S000_CALL_DCZUOPEN();
        if (pgmRtn) return;
    }
    public void B040_AC_BIND() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EACSBIND);
        EACSBIND.FUNC = 'B';
        EACSBIND.CARD_NO = DCCUOPEN.CARD_NO;
        EACSBIND.IO_FLG = DCCSELCD.IO_FLG;
        EACSBIND.CON_BNK = DCCSELCD.OTH_BNK;
        EACSBIND.CON_NME = DCCSELCD.OTH_BNK_NME;
        EACSBIND.CON_AC = DCCSELCD.BIND_AC;
        EACSBIND.AC_NM = DCCSELCD.AC_NM;
        EACSBIND.ID_TYPE = CICCUST.O_DATA.O_ID_TYPE;
        EACSBIND.ID_NO = CICCUST.O_DATA.O_ID_NO;
        S000_CALL_EAZSBIND();
        if (pgmRtn) return;
    }
    public void B060_BIND_AC_CHK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DCCSELCD.BIND_AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            IBS.init(SCCGWA, DCCPCDCK);
            DCCPCDCK.CARD_NO = DCCSELCD.BIND_AC;
            DCCPCDCK.FUNC_CODE = 'P';
            DCCPCDCK.CARD_PSW = DCCSELCD.PSW1;
            S000_CALL_DCZPCDCK();
            if (pgmRtn) return;
        }
    }
    public void B050_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCOELCD);
        DCCOELCD.CARD_NO = DCCUOPEN.CARD_NO;
        DCCOELCD.CI_NO = DCCSELCD.CI_NO;
        DCCOELCD.PROD_COD = DCCSELCD.PROD_CD;
        DCCOELCD.AC_NM = DCCSELCD.AC_NM;
        DCCOELCD.AC_TYP = DCCSELCD.AC_TYP;
        DCCOELCD.FACE_FLG = DCCSELCD.FACE_FLG;
        DCCOELCD.PSW1 = DCCSELCD.PSW1;
        DCCOELCD.PSW2 = DCCSELCD.PSW2;
        DCCOELCD.IO_FLG = DCCSELCD.IO_FLG;
        DCCOELCD.OTH_BNK = DCCSELCD.OTH_BNK;
        DCCOELCD.OTH_BKNM = DCCSELCD.OTH_BNK_NME;
        DCCOELCD.BIND_AC = DCCSELCD.BIND_AC;
        DCCOELCD.OWN_BR = DCCUOPEN.OWN_BR;
        DCCOELCD.POS_LMT = DCCSELCD.POS_LMT;
        DCCOELCD.TRA_LMT = DCCSELCD.TRA_LMT;
        DCCOELCD.POS_CNT = DCCSELCD.POS_CNT;
        DCCOELCD.TRA_CNT = DCCSELCD.TRA_CNT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCOELCD;
        SCCFMT.DATA_LEN = 738;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_FIRST_DCTDCCLS() throws IOException,SQLException,Exception {
        DCTDCCLS_RD = new DBParm();
        DCTDCCLS_RD.TableName = "DCTDCCLS";
        DCTDCCLS_RD.where = "CARD_PROD_CD = :DCRDCCLS.KEY.CARD_PROD_CD";
        DCTDCCLS_RD.fst = true;
        IBS.READ(SCCGWA, DCRDCCLS, this, DCTDCCLS_RD);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CICCUST RETURN");
        CEP.TRC(SCCGWA, CICCUST);
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
        CEP.TRC(SCCGWA, "CICCUST RETURN");
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase("CI3002")) {
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUOPEN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_DCZUOPEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-OPEN-CARD", DCCUOPEN);
    }
    public void S000_CALL_EAZSBIND() throws IOException,SQLException,Exception {
        EAZSBIND EAZSBIND = new EAZSBIND();
        EAZSBIND.MP(SCCGWA, EACSBIND);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK", DCCPCDCK);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DCCSELCD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCSELCD=");
            CEP.TRC(SCCGWA, DCCSELCD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
