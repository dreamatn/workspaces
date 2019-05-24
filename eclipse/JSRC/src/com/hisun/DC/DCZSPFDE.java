package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.LN.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSPFDE {
    DBParm DCTPFDE_RD;
    DBParm DCTDDLN_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUT_FMT = "DC270";
    String K_HIS_CPB_NM = "DCZSPFDE";
    String K_HIS_RMKS = "IR(DEPOSIT FOR LOANS) PRD PARM MAINTENANCE";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DCCPFDEO DCCPFDEO = new DCCPFDEO();
    DCRPFDE DCRPFDE = new DCRPFDE();
    DCRDDLN DCRDDLN = new DCRDDLN();
    LNRCTLPM LNRCTLPM = new LNRCTLPM();
    SCCGWA SCCGWA;
    DCCSPFDE DCCSPFDE;
    public void MP(SCCGWA SCCGWA, DCCSPFDE DCCSPFDE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSPFDE = DCCSPFDE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSPFDE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "************INPUT************");
        CEP.TRC(SCCGWA, DCCSPFDE.FUNC);
        CEP.TRC(SCCGWA, DCCSPFDE.AGT_NO);
        CEP.TRC(SCCGWA, DCCSPFDE.AGT_TYP);
        CEP.TRC(SCCGWA, DCCSPFDE.LN_AC);
        CEP.TRC(SCCGWA, DCCSPFDE.DD_AC);
        CEP.TRC(SCCGWA, DCCSPFDE.INC_AMT);
        CEP.TRC(SCCGWA, DCCSPFDE.DED_AMT);
        CEP.TRC(SCCGWA, DCCSPFDE.DED_PER);
        CEP.TRC(SCCGWA, DCCSPFDE.DED_DATE);
        if (DCCSPFDE.FUNC == 'Q') {
            B030_QUERY_PROCESS();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSPFDE.FUNC == 'A') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B050_ADD_PROCESS();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSPFDE.FUNC == 'M') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B070_MODIFY_PROCESS();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSPFDE.FUNC == 'D') {
            B090_DELETE_PROCESS();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DCCSPFDE.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCSPFDE.FUNC == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSPFDE.AGT_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSPFDE.LN_AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LN_AC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSPFDE.DD_AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSPFDE.AGT_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ADP_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DCRDDLN.KEY.AGT_NO = DCCSPFDE.AGT_NO;
        T000_READ_DCTDDLN();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PRD_NOT_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSPFDE.AGT_TYP != DCRDDLN.AGT_TYP) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_TYP_NOT_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!DCCSPFDE.LN_AC.equalsIgnoreCase(DCRDDLN.LN_AC)) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_NOT_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!DCCSPFDE.DD_AC.equalsIgnoreCase(DCRDDLN.DD_AC)) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_NOT_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSPFDE.AGT_NO);
        IBS.init(SCCGWA, DCRPFDE);
        DCRPFDE.KEY.AGT_NO = DCCSPFDE.AGT_NO;
        T000_READ_DCTPFDE();
        if (pgmRtn) return;
    }
    public void B050_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRPFDE);
        DCRPFDE.KEY.AGT_NO = DCCSPFDE.AGT_NO;
        DCRPFDE.AGT_TYP = DCCSPFDE.AGT_TYP;
        DCRPFDE.LN_AC = DCCSPFDE.LN_AC;
        DCRPFDE.DD_AC = DCCSPFDE.DD_AC;
        DCRPFDE.INC_AMT = DCCSPFDE.INC_AMT;
        DCRPFDE.DED_AMT = DCCSPFDE.DED_AMT;
        if (DCCSPFDE.DED_PER == ' ') DCRPFDE.DED_PER = 0;
        else DCRPFDE.DED_PER = Double.parseDouble(""+DCCSPFDE.DED_PER);
        DCRPFDE.DED_DATE = DCCSPFDE.DED_DATE;
        DCRPFDE.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRPFDE.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTPFDE();
        if (pgmRtn) return;
    }
    public void T000_REWRITE_DCTPFDE() throws IOException,SQLException,Exception {
        DCTPFDE_RD = new DBParm();
        DCTPFDE_RD.TableName = "DCTPFDE";
        IBS.REWRITE(SCCGWA, DCRPFDE, DCTPFDE_RD);
    }
    public void T000_DELETE_DCTPFDE() throws IOException,SQLException,Exception {
        DCTPFDE_RD = new DBParm();
        DCTPFDE_RD.TableName = "DCTPFDE";
        IBS.DELETE(SCCGWA, DCRPFDE, DCTPFDE_RD);
    }
    public void T000_WRITE_DCTPFDE() throws IOException,SQLException,Exception {
        DCTPFDE_RD = new DBParm();
        DCTPFDE_RD.TableName = "DCTPFDE";
        DCTPFDE_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRPFDE, DCTPFDE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CDDAT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B070_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSPFDE.AGT_NO);
        IBS.init(SCCGWA, DCRPFDE);
        DCRPFDE.KEY.AGT_NO = DCCSPFDE.AGT_NO;
        T000_READ_UPDATE_DCTPFDE();
        if (pgmRtn) return;
        DCRPFDE.KEY.AGT_NO = DCCSPFDE.AGT_NO;
        DCRPFDE.AGT_TYP = DCCSPFDE.AGT_TYP;
        DCRPFDE.LN_AC = DCCSPFDE.LN_AC;
        DCRPFDE.DD_AC = DCCSPFDE.DD_AC;
        DCRPFDE.INC_AMT = DCCSPFDE.INC_AMT;
        DCRPFDE.DED_AMT = DCCSPFDE.DED_AMT;
        if (DCCSPFDE.DED_PER == ' ') DCRPFDE.DED_PER = 0;
        else DCRPFDE.DED_PER = Double.parseDouble(""+DCCSPFDE.DED_PER);
        DCRPFDE.DED_DATE = DCCSPFDE.DED_DATE;
        DCRPFDE.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRPFDE.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTPFDE();
        if (pgmRtn) return;
    }
    public void B090_DELETE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSPFDE.AGT_NO);
        IBS.init(SCCGWA, DCRPFDE);
        DCRPFDE.KEY.AGT_NO = DCCSPFDE.AGT_NO;
        T000_READ_UPDATE_DCTPFDE();
        if (pgmRtn) return;
        T000_DELETE_DCTPFDE();
        if (pgmRtn) return;
    }
    public void B130_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPFDEO);
        DCCPFDEO.FUNC = DCCSPFDE.FUNC;
        DCCPFDEO.AGT_NO = DCCSPFDE.AGT_NO;
        DCCPFDEO.AGT_TYP = DCCSPFDE.AGT_TYP;
        DCCPFDEO.LN_AC = DCCSPFDE.LN_AC;
        DCCPFDEO.DD_AC = DCCSPFDE.DD_AC;
        DCCPFDEO.INC_AMT = DCCSPFDE.INC_AMT;
        DCCPFDEO.DED_AMT = DCCSPFDE.DED_AMT;
        DCCPFDEO.DED_PER = DCCSPFDE.DED_PER;
        DCCPFDEO.DED_DATE = DCCSPFDE.DED_DATE;
        CEP.TRC(SCCGWA, "************OUTPUT************");
        IBS.init(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "FORMAT OUTPUT");
        SCCFMT.FMTID = K_OUT_FMT;
        SCCFMT.DATA_PTR = DCCPFDEO;
        SCCFMT.DATA_LEN = 157;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DCTDDLN() throws IOException,SQLException,Exception {
        DCTDDLN_RD = new DBParm();
        DCTDDLN_RD.TableName = "DCTDDLN";
        IBS.READ(SCCGWA, DCRDDLN, DCTDDLN_RD);
    }
    public void T000_READ_DCTPFDE() throws IOException,SQLException,Exception {
        DCTPFDE_RD = new DBParm();
        DCTPFDE_RD.TableName = "DCTPFDE";
        IBS.READ(SCCGWA, DCRPFDE, DCTPFDE_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PRD_NOT_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_DCTPFDE() throws IOException,SQLException,Exception {
        DCTPFDE_RD = new DBParm();
        DCTPFDE_RD.TableName = "DCTPFDE";
        DCTPFDE_RD.upd = true;
        IBS.READ(SCCGWA, DCRPFDE, DCTPFDE_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PRD_NOT_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
