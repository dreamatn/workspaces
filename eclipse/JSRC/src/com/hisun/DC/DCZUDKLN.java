package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.EA.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUDKLN {
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTPFDE_RD;
    DBParm DDTCCY_RD;
    DBParm DDTINTB_RD;
    DBParm DCTDDLN_RD;
    boolean pgmRtn = false;
    String WS_ACLNK_CON_AC = "                                ";
    String WS_ACLNK_CARD_NO = "                   ";
    String WS_ACLNK_CON_BNK = "              ";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String WS_H_CARD_NO = " ";
    char WS_H_CHG_STS = ' ';
    int WS_H_HDOV_DT = 0;
    int WS_H_HDOV_BR = 0;
    String WS_H_HDOV_TLR = " ";
    String WS_ERR_MSG = "      ";
    String WS_ERR_INFO = "                                                                                                                        ";
    String WS_PROD_DES = "                                                            ";
    String WS_FMT_CDE = "     ";
    short WS_I = 0;
    double WS_TRUE_AMT = 0;
    double WS_INC_AMT = 0;
    double WS_TRUE_DD_AMT = 0;
    double WS_TRUE_LN_AMT = 0;
    double WS_AVA_DD_AMT = 0;
    short WS_TRUE_PER = 0;
    double WS_LN_RAT = 0;
    double WS_DD_RAT = 0;
    int WS_STR_DT = 0;
    int WS_END_DT = 0;
    double WS_LN_INT = 0;
    String WS_REL_AC = " ";
    char WS_TBL_FLAG = ' ';
    char WS_PFDE_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    EACSBINQ EACSBINQ = new EACSBINQ();
    DDCUCBLH DDCUCBLH = new DDCUCBLH();
    CICQACRL CICQACRL = new CICQACRL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DDRCCY DDRCCY = new DDRCCY();
    DDRINTB DDRINTB = new DDRINTB();
    DCRPFDE DCRPFDE = new DCRPFDE();
    DCRDDLN DCRDDLN = new DCRDDLN();
    EARACLNK EARACLNK = new EARACLNK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCUDKLN DCCUDKLN;
    public void MP(SCCGWA SCCGWA, DCCUDKLN DCCUDKLN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUDKLN = DCCUDKLN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUDKLN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DCCUDKLN.IO_AREA.FUNC == '1') {
            B100_ADD_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (DCCUDKLN.IO_AREA.FUNC == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUDKLN.IO_AREA.AGT_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROL_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_LN_RAT = DCCUDKLN.IO_AREA.LN_RAT;
        CEP.TRC(SCCGWA, WS_LN_RAT);
    }
    public void B020_ADD_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (DCCUDKLN.IO_AREA.LN_AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CNTR_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_LN_RAT = DCCUDKLN.IO_AREA.LN_RAT;
    }
    public void B100_ADD_PROC() throws IOException,SQLException,Exception {
        B020_ADD_INPUT_CHK_PROC();
        if (pgmRtn) return;
        B110_GET_DCTDDLN();
        if (pgmRtn) return;
        B120_GET_DD_RAT();
        if (pgmRtn) return;
        B130_GET_AVAL_BAL();
        if (pgmRtn) return;
        B140_GET_INC_AMT();
        if (pgmRtn) return;
        T000_READ_DCTPFDE();
        if (pgmRtn) return;
        if (WS_PFDE_FLG == 'Y') {
            T000_READUPDATE_DCTPFDE();
            if (pgmRtn) return;
        }
        B150_INSERT_DCTPFDE();
        if (pgmRtn) return;
    }
    public void B110_GET_DCTDDLN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDDLN);
        DCRDDLN.LN_AC = DCCUDKLN.IO_AREA.LN_AC;
        DCRDDLN.STRDT = SCCGWA.COMM_AREA.AC_DATE;
        T000_READ_DCTDDLN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRDDLN.STRDT);
        CEP.TRC(SCCGWA, DCRDDLN.EXPDT);
        CEP.TRC(SCCGWA, DCRDDLN.DED_MIN_AMT);
        CEP.TRC(SCCGWA, DCRDDLN.DED_MAX_AMT);
    }
    public void B120_GET_DD_RAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.AC_NO = DCRDDLN.DD_AC;
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        if (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("13")) {
            WS_REL_AC = CICQACRL.O_DATA.O_REL_AC_NO;
        } else {
            WS_REL_AC = DCRDDLN.DD_AC;
        }
        CEP.TRC(SCCGWA, WS_REL_AC);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = WS_REL_AC;
        DDRCCY.CCY = DCRDDLN.CCY;
        DDRCCY.CCY_TYPE = DCRDDLN.CCY_TYPE;
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        WS_AVA_DD_AMT = DDRCCY.CURR_BAL;
        CEP.TRC(SCCGWA, WS_AVA_DD_AMT);
        IBS.init(SCCGWA, DDRINTB);
        DDRINTB.KEY.IBS_HASH = DDRCCY.KEY.IBS_HASH;
        DDRINTB.KEY.AC = DDRCCY.KEY.AC;
        DDRINTB.KEY.TYPE = 'D';
        T000_READ_DDTINTB();
        if (pgmRtn) return;
        WS_DD_RAT = DDRINTB.TIR_RATE1;
        CEP.TRC(SCCGWA, WS_DD_RAT);
    }
    public void B130_GET_AVAL_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        SCCCLDT.MTHS = -1;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_STR_DT = SCCCLDT.DATE2;
        WS_END_DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, WS_STR_DT);
        CEP.TRC(SCCGWA, WS_END_DT);
        IBS.init(SCCGWA, DDCUCBLH);
        DDCUCBLH.I_DATA.AC = DDRCCY.KEY.AC;
        DDCUCBLH.I_DATA.STR_DT = WS_STR_DT;
        DDCUCBLH.I_DATA.END_DT = WS_END_DT;
        S000_CALL_DDZUCBLH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCUCBLH.O_DATA.AVE_BAL);
        WS_AVA_DD_AMT = DDCUCBLH.O_DATA.AVE_BAL;
        CEP.TRC(SCCGWA, WS_AVA_DD_AMT);
    }
    public void B140_GET_INC_AMT() throws IOException,SQLException,Exception {
        WS_TRUE_DD_AMT = WS_AVA_DD_AMT * ( DCRDDLN.DD_DED_PER / 100 );
        CEP.TRC(SCCGWA, WS_TRUE_DD_AMT);
        WS_TRUE_LN_AMT = DCCUDKLN.IO_AREA.LN_BAL * ( DCRDDLN.LN_DED_PER / 100 );
        CEP.TRC(SCCGWA, WS_TRUE_LN_AMT);
        if (WS_TRUE_DD_AMT > WS_TRUE_LN_AMT) {
            WS_TRUE_AMT = WS_TRUE_LN_AMT;
            WS_TRUE_PER = (short) DCRDDLN.LN_DED_PER;
        } else {
            WS_TRUE_AMT = WS_TRUE_DD_AMT;
            WS_TRUE_PER = (short) DCRDDLN.DD_DED_PER;
        }
        if (WS_TRUE_AMT < DCRDDLN.DED_MIN_AMT) {
            WS_TRUE_AMT = 0;
        }
        if (WS_TRUE_AMT > DCRDDLN.DED_MAX_AMT) {
            WS_TRUE_AMT = DCRDDLN.DED_MAX_AMT;
        }
        if (DCRDDLN.STRDT > SCCGWA.COMM_AREA.AC_DATE 
            || DCRDDLN.EXPDT <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_TRUE_AMT = 0;
        }
        WS_INC_AMT = ( WS_LN_RAT - WS_DD_RAT ) * WS_TRUE_AMT / 12 / 100;
        CEP.TRC(SCCGWA, WS_INC_AMT);
        WS_LN_INT = DCCUDKLN.IO_AREA.LN_BAL * DCCUDKLN.IO_AREA.LN_RAT / 12 / 100;
        CEP.TRC(SCCGWA, WS_LN_INT);
        DCCUDKLN.IO_AREA.INC_AMT = WS_INC_AMT;
        CEP.TRC(SCCGWA, DCCUDKLN.IO_AREA.INC_AMT);
    }
    public void B150_INSERT_DCTPFDE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRPFDE);
        DCRPFDE.KEY.AGT_NO = DCRDDLN.KEY.AGT_NO;
        DCRPFDE.KEY.TR_DATE = DCCUDKLN.IO_AREA.TR_DATE;
        DCRPFDE.KEY.JRN_NO = DCCUDKLN.IO_AREA.JRN_NO;
        DCRPFDE.AGT_TYP = DCRDDLN.AGT_TYP;
        DCRPFDE.LN_AC = DCRDDLN.LN_AC;
        DCRPFDE.DD_AC = DCRDDLN.DD_AC;
        DCRPFDE.STR_DT = WS_STR_DT;
        DCRPFDE.END_DT = WS_END_DT;
        DCRPFDE.DAY_AVE_BAL = WS_AVA_DD_AMT;
        DCRPFDE.LN_INT_AMT = DCCUDKLN.IO_AREA.INT_AMT;
        DCRPFDE.INC_AMT = WS_INC_AMT;
        DCRPFDE.DED_AMT = WS_TRUE_AMT;
        DCRPFDE.DED_PER = WS_TRUE_PER;
        DCRPFDE.DED_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRPFDE.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRPFDE.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, DCRPFDE.KEY.AGT_NO);
        CEP.TRC(SCCGWA, DCRPFDE.KEY.JRN_NO);
        CEP.TRC(SCCGWA, DCRPFDE.AGT_TYP);
        CEP.TRC(SCCGWA, DCRPFDE.LN_AC);
        CEP.TRC(SCCGWA, DCRPFDE.DD_AC);
        CEP.TRC(SCCGWA, DCRPFDE.DAY_AVE_BAL);
        CEP.TRC(SCCGWA, DCRPFDE.LN_INT_AMT);
        CEP.TRC(SCCGWA, DCRPFDE.INC_AMT);
        CEP.TRC(SCCGWA, DCRPFDE.DED_AMT);
        CEP.TRC(SCCGWA, DCRPFDE.DED_PER);
        if (WS_PFDE_FLG == 'Y') {
            T000_REWRITE_DCTPFDE();
            if (pgmRtn) return;
        } else {
            T000_WRITE_DCTPFDE();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUCBLH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-DDZUCBLH", DDCUCBLH);
        if (DDCUCBLH.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCUCBLH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUDKLN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
    }
    public void T000_READ_DCTPFDE() throws IOException,SQLException,Exception {
        DCRPFDE.KEY.AGT_NO = DCRDDLN.KEY.AGT_NO;
        DCRPFDE.KEY.TR_DATE = DCCUDKLN.IO_AREA.TR_DATE;
        DCRPFDE.KEY.JRN_NO = DCCUDKLN.IO_AREA.JRN_NO;
        DCTPFDE_RD = new DBParm();
        DCTPFDE_RD.TableName = "DCTPFDE";
        IBS.READ(SCCGWA, DCRPFDE, DCTPFDE_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PFDE_FLG = 'Y';
        } else {
            WS_PFDE_FLG = 'N';
        }
    }
    public void T000_READUPDATE_DCTPFDE() throws IOException,SQLException,Exception {
        DCTPFDE_RD = new DBParm();
        DCTPFDE_RD.TableName = "DCTPFDE";
        DCTPFDE_RD.upd = true;
        IBS.READ(SCCGWA, DCRPFDE, DCTPFDE_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_REWRITE_DCTPFDE() throws IOException,SQLException,Exception {
        DCTPFDE_RD = new DBParm();
        DCTPFDE_RD.TableName = "DCTPFDE";
        IBS.REWRITE(SCCGWA, DCRPFDE, DCTPFDE_RD);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        }
    }
    public void T000_READ_DDTINTB() throws IOException,SQLException,Exception {
        DDTINTB_RD = new DBParm();
        DDTINTB_RD.TableName = "DDTINTB";
        IBS.READ(SCCGWA, DDRINTB, DDTINTB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTINTB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        }
    }
    public void T000_READ_DCTDDLN() throws IOException,SQLException,Exception {
        DCTDDLN_RD = new DBParm();
        DCTDDLN_RD.TableName = "DCTDDLN";
        DCTDDLN_RD.where = "LN_AC = :DCRDDLN.LN_AC "
            + "AND LN_TYP = '0' "
            + "AND STRDT <= :DCRDDLN.STRDT "
            + "AND EXPDT > :DCRDDLN.STRDT";
        DCTDDLN_RD.fst = true;
        IBS.READ(SCCGWA, DCRDDLN, this, DCTDDLN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTDDLN";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        }
    }
    public void T000_WRITE_DCTPFDE() throws IOException,SQLException,Exception {
        DCTPFDE_RD = new DBParm();
        DCTPFDE_RD.TableName = "DCTPFDE";
        IBS.WRITE(SCCGWA, DCRPFDE, DCTPFDE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTPFDE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        }
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
